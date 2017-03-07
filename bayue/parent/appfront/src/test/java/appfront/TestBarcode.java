package appfront;

import ng.bayue.util.barcode.BarcodeProductor;

public class TestBarcode {
	
	public static void main(String[] args) {
		String insetImage = "E://test//abcd.jpg";
		String barcodeImage = "E://test//testappfront.jpg";
		String content = "http://192.168.9.141:8090/appfront/test/mobile.htm";
		int width = 275, height = 275;
		BarcodeProductor.encode(content, width, height, insetImage, barcodeImage);
	}
	
}
