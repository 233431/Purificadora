import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistroVentas {
    private List<Venta> ventas;

   
    public RegistroVentas() {
        ventas = new ArrayList<>();
    }

    public void agregarVenta(Venta venta) {
        ventas.add(venta);
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public double calcularCorteCaja() {
        double totalVentas = 0;

        for (Venta venta : ventas) {
            if (!venta.esVentaDeInventario()) {
                totalVentas += venta.getTotal();
            }
        }

        return totalVentas;
    }

    // Método para calcular el corte de caja al final del día
    public double calcularCorteCajaDiario(LocalDate fecha) {
        double totalVentasDiarias = 0;
        for (Venta venta : ventas) {
            LocalDate fechaVenta = venta.getFecha();
            if (fechaVenta != null && fechaVenta.equals(fecha)) {
                totalVentasDiarias += venta.getTotal();
            }
        }
        return totalVentasDiarias;
    }
    
    // Método para calcular el corte de caja semanal
    public double calcularCorteCajaSemanal(LocalDate fecha) {
        double totalVentasSemanal = 0;
        LocalDate inicioSemana = fecha.minusDays(fecha.getDayOfWeek().getValue() - 1);
        LocalDate finSemana = inicioSemana.plusDays(6);

        for (Venta venta : ventas) {
            LocalDate fechaVenta = venta.getFecha();
            if (fechaVenta.isAfter(inicioSemana) && fechaVenta.isBefore(finSemana.plusDays(1))) {
                totalVentasSemanal += venta.getTotal();
            }
        }
        return totalVentasSemanal;
    }

    // Método para calcular el corte de caja mensual
    public double calcularCorteCajaMensual(LocalDate fecha) {
        double totalVentasMensual = 0;
        LocalDate inicioMes = fecha.withDayOfMonth(1);
        LocalDate finMes = inicioMes.plusMonths(1).minusDays(1);

        for (Venta venta : ventas) {
            LocalDate fechaVenta = venta.getFecha();
            if (fechaVenta.isAfter(inicioMes) && fechaVenta.isBefore(finMes.plusDays(1))) {
                totalVentasMensual += venta.getTotal();
            }
        }
        return totalVentasMensual;
    }
}
