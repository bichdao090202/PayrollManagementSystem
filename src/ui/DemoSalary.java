package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.Character.UnicodeScript;
import java.net.MalformedURLException;
import java.util.List;

import javax.swing.JFrame;

import org.w3c.dom.html.HTMLBaseFontElement;

import com.itextpdf.io.codec.Base64;
import com.itextpdf.io.font.FontCache;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.otf.Glyph;
import com.itextpdf.io.font.otf.GlyphLine;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDictionary;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfOutputStream;
import com.itextpdf.kernel.pdf.PdfString;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.font.FontSelector;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.styledxmlparser.jsoup.nodes.Element;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;

public class DemoSalary extends JFrame {
	public static final String fontString = "C:\\Windows\\Fonts\\arial.ttf";
	
	public DemoSalary() throws IOException, DocumentException {
		// TODO Auto-generated constructor stub
		try {
			String path = "tableSalaryEployee/salaryDemo.pdf";
			PdfWriter pdfWriter = new PdfWriter(path);
			PdfDocument pdfDocument = new PdfDocument(pdfWriter);
			pdfDocument.addNewPage();
			Document document = new Document(pdfDocument);
			pdfDocument.setDefaultPageSize(PageSize.A4);
			
			PdfFont fontUtf8 = PdfFontFactory.createFont(fontString, BaseFont.IDENTITY_H, true);
			
			
			String nameCity = "Công Ty TNHH Group 14";
			Paragraph paragraphNameCity = new Paragraph(nameCity);
			paragraphNameCity.setTextAlignment(TextAlignment.CENTER);
			paragraphNameCity.setFontSize(30f);
			
			String title = "PHIẾU LƯƠNG THÁNG 11/2022";
			Paragraph paragraphTitle = new Paragraph(title);
			paragraphTitle.setTextAlignment(TextAlignment.CENTER);
			paragraphTitle.setFontSize(15f);
			paragraphTitle.setMarginTop(30f);
			paragraphTitle.setFont(fontUtf8);
			
			
			float columnWit[] = {270f,270f,50f,270f,270f}; 
			Table table = new Table(columnWit);
			table.setMargins(30f, 0f, 30f, 60f);
			
			//row
			table.addCell(new Cell().add(new Paragraph("Tên nhân viên : "))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			table.addCell(new Cell().add(new Paragraph("Lê Thanh Toàn"))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			table.addCell(new Cell().setBorder(Border.NO_BORDER));
			table.addCell(new Cell().add(new Paragraph("Mã nhân viên : "))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			table.addCell(new Cell().add(new Paragraph("20040331"))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			
			//row
			table.addCell(new Cell().add(new Paragraph("Ngày sinh : "))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			table.addCell(new Cell().add(new Paragraph("8/4/2002"))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			table.addCell(new Cell().setBorder(Border.NO_BORDER));
			table.addCell(new Cell().add(new Paragraph("Giới tính : "))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			table.addCell(new Cell().add(new Paragraph("Nam"))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			
			//row
			table.addCell(new Cell().add(new Paragraph("Số điện thoại : "))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			table.addCell(new Cell().add(new Paragraph("0366637192"))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			table.addCell(new Cell().setBorder(Border.NO_BORDER));
			table.addCell(new Cell().add(new Paragraph("Địa chỉ : "))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			table.addCell(new Cell().add(new Paragraph("360/61 Lê Đức Thọ p6 Quận Gò vấp"))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			
			//row
			table.addCell(new Cell().add(new Paragraph("Tên ngân hàng : "))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			table.addCell(new Cell().add(new Paragraph("Agribank"))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			table.addCell(new Cell().setBorder(Border.NO_BORDER));
			table.addCell(new Cell().add(new Paragraph("Số tài khoản : "))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			table.addCell(new Cell().add(new Paragraph("422315642452"))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			
			//row
			table.addCell(new Cell().add(new Paragraph("Người hưởng : "))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			table.addCell(new Cell().add(new Paragraph("Lê Thanh Toàn"))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			table.addCell(new Cell().setBorder(Border.NO_BORDER));
			table.addCell(new Cell().add(new Paragraph("Chuyên môn/Chức vụ : "))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			table.addCell(new Cell().add(new Paragraph("Quản lý"))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			
			//row
			table.addCell(new Cell().add(new Paragraph("Mã tổ : "))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			table.addCell(new Cell().add(new Paragraph("0163566"))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			table.addCell(new Cell().setBorder(Border.NO_BORDER));
			table.addCell(new Cell().add(new Paragraph("Mã Phòng ban : "))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			table.addCell(new Cell().add(new Paragraph("0135123"))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			
			//row
			table.addCell(new Cell().add(new Paragraph("Số ngày làm : "))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			table.addCell(new Cell().add(new Paragraph("30"))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			table.addCell(new Cell().setBorder(Border.NO_BORDER));
			table.addCell(new Cell().add(new Paragraph("Số ngày nghỉ : "))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			table.addCell(new Cell().add(new Paragraph("0"))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			
			//row
			table.addCell(new Cell().add(new Paragraph("Chỉ tiêu : "))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			table.addCell(new Cell().add(new Paragraph("Đạt"))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			table.addCell(new Cell().setBorder(Border.NO_BORDER));
			table.addCell(new Cell().add(new Paragraph("Lý do : "))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			table.addCell(new Cell().add(new Paragraph("Đạt yêu cầu làm việc"))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			
			//row
			table.addCell(new Cell().add(new Paragraph("Tiền thưởng : "))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			table.addCell(new Cell().add(new Paragraph("800.000"))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			table.addCell(new Cell().setBorder(Border.NO_BORDER));
			table.addCell(new Cell().add(new Paragraph("Tiền phạt : "))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			table.addCell(new Cell().add(new Paragraph("0"))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			
			//row
			table.addCell(new Cell().add(new Paragraph("Tổng lương : "))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			table.addCell(new Cell().add(new Paragraph("20.000.000"))
					.setBorder(Border.NO_BORDER)
					.setHeight(25f)
					.setFont(fontUtf8)
			);
			
			float columnWithComfirm[] = {280f,280f};
			Table tblComfirm = new Table(columnWithComfirm);
			Paragraph comfirm = new Paragraph("Xác nhận của cơ quan chức năng");
			tblComfirm.addCell(new Cell().add(comfirm)
					.setBorder(Border.NO_BORDER)
					.setTextAlignment(TextAlignment.CENTER)
					.setFont(fontUtf8)
			);
			tblComfirm.addCell(new Cell().add(new Paragraph("Chữ ký người nhận"))
					.setBorder(Border.NO_BORDER)
					.setTextAlignment(TextAlignment.CENTER)
					.setFont(fontUtf8)
			);
			
			String image = "images//logoCity.jpg";
			ImageData imagedata = ImageDataFactory.create(image);
			Image logo = new Image(imagedata);
			logo.setMarginLeft(70f);
			
			Paragraph president = new Paragraph("GIÁM ĐỐC");
			president.setMarginLeft(90f);
			president.setFont(PdfFontFactory.createFont(FontConstants.TIMES_ROMAN));
			president.setFontColor(ColorConstants.RED);
			president.setBold();
			president.setItalic();
			president.setFont(fontUtf8);
			
			
			Paragraph sign = new Paragraph("Nguyễn Minh Quân");
			sign.setMarginLeft(70f);
			sign.setFont(PdfFontFactory.createFont(FontConstants.TIMES_ROMAN));
			sign.setFontColor(ColorConstants.RED);
			sign.setBold();
			sign.setItalic();
			sign.setFont(fontUtf8);
			
			document.add(paragraphNameCity);
			document.add(paragraphTitle);
			document.add(table);
			document.add(tblComfirm);
			document.add(logo);
			document.add(president);
			document.add(sign);
			
			
			document.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException, DocumentException {
		new DemoSalary().setVisible(true);
	}

}
