package com.gpdn.turnosmatsa.service;

import com.gpdn.turnosmatsa.model.Appointment;
import com.gpdn.turnosmatsa.model.Patient;
import com.gpdn.turnosmatsa.repository.AppointmentRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AppointmentExportService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentExportService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public byte[] exportAllToExcel() throws IOException {
        List<Appointment> appointments = appointmentRepository.findAll();

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Turnos");

            // ===== Estilos =====
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.WHITE.getIndex());
            headerStyle.setFont(headerFont);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);

            CellStyle bodyStyle = workbook.createCellStyle();
            bodyStyle.setWrapText(false);
            bodyStyle.setAlignment(HorizontalAlignment.LEFT);

            // ===== Encabezado (mismo formato de la tabla de Admin) =====
            String[] columns = {
                    "Nombre", "Apellido", "CUIL / DNI",
                    "Estudio", "Especialista",
                    "Horario", "Fecha", "Obra Social"
            };

            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerStyle);
            }

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

            // ===== Datos =====
            int rowIdx = 1;
            for (Appointment a : appointments) {
                Row row = sheet.createRow(rowIdx++);

                Patient p = a.getPatient();

                // Nombre
                Cell c0 = row.createCell(0);
                c0.setCellValue(p.getFirstName());
                c0.setCellStyle(bodyStyle);

                // Apellido
                Cell c1 = row.createCell(1);
                c1.setCellValue(p.getLastName());
                c1.setCellStyle(bodyStyle);

                // CUIL / DNI
                Cell c2 = row.createCell(2);
                c2.setCellValue(p.getDocumentNumber());
                c2.setCellStyle(bodyStyle);

                // Estudio
                Cell c3 = row.createCell(3);
                c3.setCellValue(a.getStudyType().getName());
                c3.setCellStyle(bodyStyle);

                // Especialista
                Cell c4 = row.createCell(4);
                String doctorName = a.getDoctor().getFirstName() + " " + a.getDoctor().getLastName();
                c4.setCellValue(doctorName);
                c4.setCellStyle(bodyStyle);

                // Horario
                Cell c5 = row.createCell(5);
                c5.setCellValue(a.getStartDateTime().toLocalTime().format(timeFormatter));
                c5.setCellStyle(bodyStyle);

                // Fecha
                Cell c6 = row.createCell(6);
                c6.setCellValue(a.getStartDateTime().toLocalDate().format(dateFormatter));
                c6.setCellStyle(bodyStyle);

                // Obra social
                Cell c7 = row.createCell(7);
                c7.setCellValue(p.getInsuranceName());
                c7.setCellStyle(bodyStyle);
            }

            // Auto-size columnas
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);
            return out.toByteArray();
        }
    }
}

