package com.juan.app_inventario.repositories;

import com.juan.app_inventario.models.OrdenCompra;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenDeCompraRep extends JpaRepository<OrdenCompra, Long> {

    @Query("select u from OrdenCompra u where u.estado = :est and u.fechaOrden between :fechaDes and :fechaHas")
    public List<OrdenCompra> buscarPersonalizado(
            @Param("est") String estado, 
            @Param("fechaDes")  String fechaDesde, 
            @Param("fechaHas") String fechaHasta);

    @Query("select u from OrdenCompra u where u.proveedor.id = :id_prov")
    public List<OrdenCompra> obtenerPorProveedor(@Param("id_prov") Long proveedorId);

    public List<OrdenCompra> findByfechaOrden(LocalDate fecha);
    
}
