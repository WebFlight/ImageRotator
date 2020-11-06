package imagerotator.usecases;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;


import imagerotator.entities.DataValidationException;

public class ImageRotator {

	public BufferedImage rotate(BufferedImage image, String directionLowerCase) {

		double rads;
		switch (directionLowerCase) {
		case "clockwise":
			rads = Math.toRadians(90);
			break;

		// Only other option is counterclockwise
		case "counterclockwise":
			rads = Math.toRadians(-90);
			break;

		default:
			rads = 0;
			throw new DataValidationException("Invalid direction present. Rotating failed.");

		}
		final double sin = Math.abs(Math.sin(rads));
		final double cos = Math.abs(Math.cos(rads));
		final int w = (int) Math.floor(image.getWidth() * cos + image.getHeight() * sin);
		final int h = (int) Math.floor(image.getHeight() * cos + image.getWidth() * sin);
		final BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
		final AffineTransform at = new AffineTransform();
		at.translate(w / 2, h / 2);
		at.rotate(rads, 0, 0);
		at.translate(-image.getWidth() / 2, -image.getHeight() / 2);

		final AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		rotateOp.filter(image, rotatedImage);
		return rotatedImage;

	}

}
