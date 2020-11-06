package imagerotator.usecases;

import imagerotator.entities.DataValidationException;
import system.proxies.Image;

public class ImageRotatorInputValidator {

	public void validate(String directionLowerCase, Image inputImage, long maxFileSizeKiloByte, String imageExtension) {
		validateImageGiven(inputImage);
		validateImageContents(inputImage);
		validateImageSize(inputImage, maxFileSizeKiloByte);
		validateImageDirection(directionLowerCase);
		validateImageExtension(imageExtension);
	}

	private void validateImageGiven(Image inputImage) {
		if (inputImage == null) {
			throw new DataValidationException("No image provided.");
		}
	}

	private void validateImageContents(Image inputImage) {
		if (inputImage.getHasContents() == false) {
			throw new DataValidationException("The input image has no contents.");
		}

	}

	private void validateImageSize(Image inputImage, long maxFileSizeKiloByte) {
		if (inputImage.getSize() > maxFileSizeKiloByte * 1000) {
			throw new DataValidationException(
					"The input image size is too big. max " + maxFileSizeKiloByte + " KB is permitted.");
		}
	}

	private void validateImageDirection(String directionLowerCase) {
		if (directionLowerCase == "clockwise" || directionLowerCase == "counterclockwise") {
			throw new DataValidationException(
					"The lowercase value of enum direction should be clockwise or counterclockwise");

		}
	}

	private void validateImageExtension(String imageExtension) {
		if (!(imageExtension.equals("jpg") || imageExtension.contentEquals("jpeg"))) {
			throw new DataValidationException("The file must be a jpg or jpeg file.");
		}
	}

}
