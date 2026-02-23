package com.tejait.batch15.serviceimpl;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.tejait.batch15.model.Employee;
import com.tejait.batch15.model.EmployeeFile;
import com.tejait.batch15.repository.EmployeeFileRepository;
import com.tejait.batch15.repository.EmployeeRepository;

@Service
public class EmployeeFileServiceImpl {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeFileRepository fileRepository;

    // Generate All Formats
    public String generateAndStoreAll() throws Exception {

        List<Employee> employees = employeeRepository.findAll();

        savePdf(employees);
        saveWord(employees);
        saveTxt(employees);

        return "All files generated and stored successfully!";
    }

    // ================= PDF =================
    private void savePdf(List<Employee> employees) throws Exception {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, out);

        document.open();
        document.add(new Paragraph(
                "ID | First Name | Last Name | Full Name | Dept | Age | Salary | Emp Code\n\n"));

        for (Employee e : employees) {

            document.add(new Paragraph(
                    e.getId() + " | " +
                    e.getFname() + " | " +
                    e.getLname() + " | " +
                    e.getFullname() + " | " +
                    e.getDept() + " | " +
                    e.getAge() + " | " +
                    e.getSalary() + " | " +
                    e.getEmpCode()
            ));
        }


        document.close();

        storeFile("employees.pdf",
                "application/pdf",
                out.toByteArray());
    }

    // ================= WORD =================
    private void saveWord(List<Employee> employees) throws Exception {

    	 XWPFDocument document = new XWPFDocument();
    	 
    	 ByteArrayOutputStream out = new ByteArrayOutputStream();
    	
    	XWPFParagraph header = document.createParagraph();
    	XWPFRun headerRun = header.createRun();
    	headerRun.setBold(true);
    	headerRun.setText("ID | First Name | Last Name | Full Name | Dept | Age | Salary | Emp Code");

    	for (Employee e : employees) {

    	    XWPFParagraph paragraph = document.createParagraph();
    	    XWPFRun run = paragraph.createRun();

    	    run.setText(
    	            e.getId() + " | " +
    	            e.getFname() + " | " +
    	            e.getLname() + " | " +
    	            e.getFullname() + " | " +
    	            e.getDept() + " | " +
    	            e.getAge() + " | " +
    	            e.getSalary() + " | " +
    	            e.getEmpCode()
    	    );
    	}


        document.write(out);
        document.close();

        storeFile("employees.docx",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                out.toByteArray());
    }

    // ================= TXT =================
    private void saveTxt(List<Employee> employees) {

        StringBuilder builder = new StringBuilder();

        builder.append("ID | First Name | Last Name | Full Name | Dept | Age | Salary | Emp Code\n");
        builder.append("--------------------------------------------------------------------------\n");

        for (Employee e : employees) {

            builder.append(e.getId()).append(" | ")
                    .append(e.getFname()).append(" | ")
                    .append(e.getLname()).append(" | ")
                    .append(e.getFullname()).append(" | ")
                    .append(e.getDept()).append(" | ")
                    .append(e.getAge()).append(" | ")
                    .append(e.getSalary()).append(" | ")
                    .append(e.getEmpCode())
                    .append("\n");
        }

        storeFile("employees.txt",
                "text/plain",
                builder.toString().getBytes());
    }

    // Common method
    private void storeFile(String name, String type, byte[] data) {

        EmployeeFile file = new EmployeeFile();
        file.setFileName(name);
        file.setFileType(type);
        file.setFileData(data);

        fileRepository.save(file);
    }

    // Download by Type
    public EmployeeFile getFileByType(String type) {

        String mimeType = switch (type.toLowerCase()) {
            case "pdf" -> "application/pdf";
            case "docx" ->
                    "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
            case "txt" -> "text/plain";
            default -> throw new RuntimeException("Invalid type");
        };

        return fileRepository
                .findFirstByFileType(mimeType)
                .orElseThrow(() ->
                        new RuntimeException("File not found"));
    }
}

