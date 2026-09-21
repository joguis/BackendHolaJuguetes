package co.holajuguetes.inventario.services.services;

import co.holajuguetes.inventario.enums.TipoMovimiento;
import co.holajuguetes.inventario.exceptions.ResourceNotFoundException;
import co.holajuguetes.inventario.models.*;
import co.holajuguetes.inventario.repositories.*;
import co.holajuguetes.inventario.services.DTO.*;
import co.holajuguetes.inventario.services.Mappers.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventarioCrudService {
    private final CategoriaRepository categoriaRepository;
    private final SubcategoriaRepository subcategoriaRepository;
    private final ProveedorRepository proveedorRepository;
    private final GrupoVentaRepository grupoVentaRepository;
    private final ProductoRepository productoRepository;
    private final MovimientoInventarioRepository movimientoRepository;
    private final CategoriaMapper categoriaMapper;
    private final SubcategoriaMapper subcategoriaMapper;
    private final ProveedorMapper proveedorMapper;
    private final GrupoVentaMapper grupoVentaMapper;
    private final ProductoMapper productoMapper;
    private final MovimientoInventarioMapper movimientoMapper;
    private final Map<String, AtomicInteger> referencias = new ConcurrentHashMap<>();

    private Pageable page(OperacionRequest request, String sort) {
        int number = request.getPage() == null || request.getPage() < 0 ? 0 : request.getPage();
        int size = request.getSize() == null || request.getSize() < 1 || request.getSize() > 100 ? 20 : request.getSize();
        return PageRequest.of(number, size, Sort.by(sort));
    }

    private String required(String value, String field) {
        if (value == null || value.trim().isEmpty()) throw new IllegalArgumentException(field + " es obligatorio");
        return value.trim();
    }

    private <T> T required(T value, String field) {
        if (value == null) throw new IllegalArgumentException(field + " es obligatorio");
        return value;
    }

    private Categoria categoria(Integer id) { return categoriaRepository.findById(required(id, "categoriaId")).orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada")); }
    private Subcategoria subcategoria(Integer id) { return subcategoriaRepository.findById(required(id, "subcategoriaId")).orElseThrow(() -> new ResourceNotFoundException("Subcategoría no encontrada")); }
    private Proveedor proveedor(Integer id) { return proveedorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Proveedor no encontrado")); }
    private GrupoVenta grupo(Integer id) { return grupoVentaRepository.findById(required(id, "grupoVentaId")).orElseThrow(() -> new ResourceNotFoundException("Grupo de venta no encontrado")); }
    private Producto producto(Integer id) { return productoRepository.findById(required(id, "productoId")).orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado")); }
    private MovimientoInventario movimiento(Integer id) { return movimientoRepository.findById(required(id, "id")).orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrado")); }

    @Transactional
    public CategoriaResponse crearCategoria(OperacionRequest r) {
        String nombre = required(r.getNombre(), "nombre");
        if (categoriaRepository.existsByNombreIgnoreCase(nombre)) throw new IllegalArgumentException("La categoría ya existe");
        return categoriaMapper.toResponse(categoriaRepository.save(Categoria.builder().nombre(nombre).descripcion(r.getDescripcion()).build()));
    }
    @Transactional
    public CategoriaResponse editarCategoria(OperacionRequest r) {
        Categoria e = categoria(r.getId());
        if (r.getNombre() != null) e.setNombre(required(r.getNombre(), "nombre"));
        if (r.getDescripcion() != null) e.setDescripcion(r.getDescripcion());
        return categoriaMapper.toResponse(categoriaRepository.save(e));
    }
    @Transactional(readOnly = true)
    public Page<CategoriaResponse> listarCategorias(OperacionRequest r) { return categoriaRepository.findAll(page(r, "id")).map(categoriaMapper::toResponse); }
    @Transactional(readOnly = true)
    public Page<CategoriaResponse> buscarCategorias(OperacionRequest r) { return categoriaRepository.findByNombreContainingIgnoreCase(required(r.getTermino(), "termino"), page(r, "nombre")).map(categoriaMapper::toResponse); }
    @Transactional
    public void eliminarCategoria(OperacionRequest r) { categoriaRepository.delete(categoria(r.getId())); }

    @Transactional
    public SubcategoriaResponse crearSubcategoria(OperacionRequest r) {
        String nombre = required(r.getNombre(), "nombre");
        if (subcategoriaRepository.existsByNombreIgnoreCase(nombre)) throw new IllegalArgumentException("La subcategoría ya existe");
        return subcategoriaMapper.toResponse(subcategoriaRepository.save(Subcategoria.builder().nombre(nombre).build()));
    }
    @Transactional
    public SubcategoriaResponse editarSubcategoria(OperacionRequest r) { Subcategoria e = subcategoria(r.getId()); if (r.getNombre() != null) e.setNombre(required(r.getNombre(), "nombre")); return subcategoriaMapper.toResponse(subcategoriaRepository.save(e)); }
    @Transactional(readOnly = true)
    public Page<SubcategoriaResponse> listarSubcategorias(OperacionRequest r) { return subcategoriaRepository.findAll(page(r, "id")).map(subcategoriaMapper::toResponse); }
    @Transactional(readOnly = true)
    public Page<SubcategoriaResponse> buscarSubcategorias(OperacionRequest r) { return subcategoriaRepository.findByNombreContainingIgnoreCase(required(r.getTermino(), "termino"), page(r, "nombre")).map(subcategoriaMapper::toResponse); }
    @Transactional
    public void eliminarSubcategoria(OperacionRequest r) { subcategoriaRepository.delete(subcategoria(r.getId())); }

    @Transactional
    public ProveedorResumenResponse crearProveedor(OperacionRequest r) { return proveedorMapper.toResumenResponse(proveedorRepository.save(Proveedor.builder().nombre(required(r.getNombre(), "nombre")).telefono(r.getTelefono()).email(r.getEmail()).build())); }
    @Transactional
    public ProveedorResumenResponse editarProveedor(OperacionRequest r) { Proveedor e = proveedor(r.getId()); if (r.getNombre()!=null) e.setNombre(required(r.getNombre(), "nombre")); if (r.getTelefono()!=null) e.setTelefono(r.getTelefono()); if (r.getEmail()!=null) e.setEmail(r.getEmail()); return proveedorMapper.toResumenResponse(proveedorRepository.save(e)); }
    @Transactional(readOnly = true)
    public java.util.List<ProveedorResumenResponse> listarProveedores(OperacionRequest r) { return proveedorRepository.findAll(Sort.by("id")).stream().map(proveedorMapper::toResumenResponse).toList(); }
    @Transactional(readOnly = true)
    public java.util.List<ProveedorResumenResponse> buscarProveedores(OperacionRequest r) { return proveedorRepository.findByNombreContainingIgnoreCase(required(r.getTermino(), "termino")).stream().map(proveedorMapper::toResumenResponse).toList(); }
    @Transactional
    public void eliminarProveedor(OperacionRequest r) { proveedorRepository.delete(proveedor(r.getId())); }

    @Transactional
    public GrupoVentaResponse crearGrupo(OperacionRequest r) { GrupoVenta e = GrupoVenta.builder().nombre(r.getNombre()).categoria(categoria(r.getCategoriaId())).subcategoria(subcategoria(r.getSubcategoriaId())).precioVenta(required(r.getPrecioVenta(), "precioVenta")).build(); return grupoVentaMapper.toResponse(grupoVentaRepository.save(e)); }
    @Transactional
    public GrupoVentaResponse editarGrupo(OperacionRequest r) { GrupoVenta e = grupo(r.getId()); if (r.getNombre()!=null) e.setNombre(r.getNombre()); if (r.getCategoriaId()!=null) e.setCategoria(categoria(r.getCategoriaId())); if (r.getSubcategoriaId()!=null) e.setSubcategoria(subcategoria(r.getSubcategoriaId())); if (r.getPrecioVenta()!=null) e.setPrecioVenta(r.getPrecioVenta()); return grupoVentaMapper.toResponse(grupoVentaRepository.save(e)); }
    @Transactional(readOnly = true)
    public Page<GrupoVentaResponse> listarGrupos(OperacionRequest r) { return grupoVentaRepository.findAll(page(r, "id")).map(grupoVentaMapper::toResponse); }
    @Transactional(readOnly = true)
    public Page<GrupoVentaResponse> buscarGrupos(OperacionRequest r) { String term=required(r.getTermino(), "termino"); return grupoVentaRepository.findByNombreContainingIgnoreCase(term, page(r, "nombre")).map(grupoVentaMapper::toResponse); }
    @Transactional
    public void eliminarGrupo(OperacionRequest r) { grupoVentaRepository.delete(grupo(r.getId())); }

    @Transactional
    public ProductoResponse crearProducto(OperacionRequest r) { Producto e = Producto.builder().sku(required(r.getSku(), "sku")).nombre(required(r.getNombre(), "nombre")).grupoVenta(grupo(r.getGrupoVentaId())).proveedor(r.getProveedorId()==null?null:proveedor(r.getProveedorId())).precioCosto(r.getPrecioCosto()==null?BigDecimal.ZERO:r.getPrecioCosto()).cantidad(r.getCantidad()==null?0:r.getCantidad()).activo(r.getActivo()==null?true:r.getActivo()).build(); return productoMapper.toResponse(productoRepository.save(e)); }
    @Transactional
    public ProductoResponse editarProducto(OperacionRequest r) { Producto e=producto(r.getId()); if(r.getNombre()!=null)e.setNombre(required(r.getNombre(), "nombre")); if(r.getGrupoVentaId()!=null)e.setGrupoVenta(grupo(r.getGrupoVentaId())); if(r.getProveedorId()!=null)e.setProveedor(proveedor(r.getProveedorId())); if(r.getPrecioCosto()!=null)e.setPrecioCosto(r.getPrecioCosto()); if(r.getActivo()!=null)e.setActivo(r.getActivo()); return productoMapper.toResponse(productoRepository.save(e)); }
    @Transactional(readOnly = true)
    public Page<ProductoResponse> listarProductos(OperacionRequest r) { return productoRepository.findAll(page(r,"id")).map(productoMapper::toResponse); }
    @Transactional(readOnly = true)
    public Page<ProductoResponse> buscarProductos(OperacionRequest r) { String term=required(r.getTermino(), "termino"); return productoRepository.findByNombreContainingIgnoreCase(term,page(r,"nombre")).map(productoMapper::toResponse); }
    @Transactional
    public void eliminarProducto(OperacionRequest r) { productoRepository.delete(producto(r.getId())); }

    private String prefijo(TipoMovimiento tipo) { return tipo == TipoMovimiento.ENTRADA ? "Fact" : tipo == TipoMovimiento.SALIDA ? "Ticket" : tipo.getValor(); }
    private synchronized String siguiente(TipoMovimiento tipo) { String prefix=prefijo(tipo); AtomicInteger n=referencias.computeIfAbsent(prefix, k -> new AtomicInteger(movimientoRepository.findAll().stream().map(MovimientoInventario::getReferencia).filter(x -> x != null && x.startsWith(prefix+" ")).map(x -> Integer.parseInt(x.substring(prefix.length()+1))).max(Comparator.naturalOrder()).orElse(0))); return prefix + " " + String.format("%03d", n.incrementAndGet()); }
    public synchronized String avanzarReferencia(TipoMovimiento tipo) { return siguiente(required(tipo, "tipoMovimiento")); }
    private String referenciaActiva(TipoMovimiento tipo) { String prefix=prefijo(tipo); AtomicInteger n=referencias.computeIfAbsent(prefix, k -> new AtomicInteger(0)); if(n.get()==0) return siguiente(tipo); return prefix + " " + String.format("%03d", n.get()); }
    @Transactional
    public MovimientoInventarioResponse crearMovimiento(OperacionRequest r) { if(r.getCantidad()==null || r.getCantidad()<=0) throw new IllegalArgumentException("cantidad debe ser mayor que cero"); TipoMovimiento tipo=required(r.getTipoMovimiento(),"tipoMovimiento"); MovimientoInventario e=MovimientoInventario.builder().producto(producto(r.getProductoId())).tipoMovimiento(tipo).cantidad(r.getCantidad()).motivo(r.getMotivo()).referencia(referenciaActiva(tipo)).build(); return movimientoMapper.toResponse(movimientoRepository.save(e)); }
    @Transactional(readOnly = true)
    public Page<MovimientoInventarioResponse> listarMovimientos(OperacionRequest r) { return movimientoRepository.findAll(page(r,"id")).map(movimientoMapper::toResponse); }
    @Transactional(readOnly = true)
    public Page<MovimientoInventarioResponse> buscarMovimientos(OperacionRequest r) { if(r.getProductoId()!=null)return movimientoRepository.findByProductoId(r.getProductoId(),page(r,"id")).map(movimientoMapper::toResponse); return movimientoRepository.findAll(page(r,"id")).map(movimientoMapper::toResponse); }
    private void compensar(MovimientoInventario e) { TipoMovimiento inverso=switch(e.getTipoMovimiento()){case ENTRADA->TipoMovimiento.SALIDA; case SALIDA,AJUSTE->TipoMovimiento.ENTRADA; case DEVOLUCION->TipoMovimiento.SALIDA;}; movimientoRepository.save(MovimientoInventario.builder().producto(e.getProducto()).tipoMovimiento(inverso).cantidad(e.getCantidad()).motivo("Compensación automática").referencia(e.getReferencia()).build()); }
    @Transactional
    public MovimientoInventarioResponse editarMovimiento(OperacionRequest r) { MovimientoInventario e=movimiento(r.getId()); if(r.getProductoId()!=null && !r.getProductoId().equals(e.getProducto().getId()) || r.getTipoMovimiento()!=null && r.getTipoMovimiento()!=e.getTipoMovimiento() || r.getCantidad()!=null && !r.getCantidad().equals(e.getCantidad())) { compensar(e); e.setProducto(producto(r.getProductoId()==null?e.getProducto().getId():r.getProductoId())); e.setTipoMovimiento(r.getTipoMovimiento()==null?e.getTipoMovimiento():r.getTipoMovimiento()); e.setCantidad(r.getCantidad()==null?e.getCantidad():r.getCantidad()); movimientoRepository.flush(); movimientoRepository.save(MovimientoInventario.builder().producto(e.getProducto()).tipoMovimiento(e.getTipoMovimiento()).cantidad(e.getCantidad()).motivo("Aplicación de edición").referencia(e.getReferencia()).build()); } if(r.getMotivo()!=null)e.setMotivo(r.getMotivo()); return movimientoMapper.toResponse(movimientoRepository.save(e)); }
    @Transactional
    public void eliminarMovimiento(OperacionRequest r) { MovimientoInventario e=movimiento(r.getId()); compensar(e); movimientoRepository.delete(e); }
}
