//package test3;
//
//import java.awt.Image;
//import javax.swing.ImageIcon;
//import java.net.URL;
//
//public class ScaledImage {
//	
//	public Image image;
//	public ImageIcon icon;
//	
//	public ScaledImage(URL path) {
//		scaleImage(path, 20, 20);
//	}
//	
//	public ScaledImage(URL path, int width, int height) {
//		scaleImage(path, width, height);
//	}
//	
//	private void scaleImage(URL path, int width, int height) {
//		ImageIcon imageIcon = new ImageIcon(path);
//		Image originalImage = imageIcon.getImage();
//		
//		//Calcute the desired width and height
//		int maxWidth = width;
//		int maxHeight = height;
//		int originalWidth = originalImage.getWidth(null);
//		int originalHeight = originalImage.getHeight(null);
//		int scaledWidth = originalWidth;
//		int scaledHeight = originalHeight;
//		
//		//Scale the image while maintaining aspect ratio
//		if(originalWidth > maxWidth) {
//			scaledWidth  = maxWidth;
//			scaledHeight = (scaledWidth * originalHeight) / originalWidth;
//		}
//		if(scaledHeight > maxHeight) {
//			scaledHeight = maxHeight;
//			scaledWidth = (scaledHeight * originalWidth) / originalHeight;
//		}
//		
//		Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
//		
//		//Create a new ImageIcon from the scaled image
//		ImageIcon scaledIcon = new ImageIcon(scaledImage);
//		
//		this.image = scaledImage;
//		this.icon = scaledIcon;
//	}
//}
