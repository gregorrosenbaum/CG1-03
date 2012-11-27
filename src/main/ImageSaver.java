package main;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import object.AxisAlignedBox;
import object.Geometry;
import object.Plane;
import object.Sphere;
import object.Triangle;
import vectorlib.Normal3;
import vectorlib.Point3;
import vectorlib.Vector3;
import Cameras.OrthographicCamera;
import Cameras.PerspectiveCamera;
import color.Color;

/**
 * A small application that uses {@link ImageCanvas} and
 * {@link ImageIO#write()} to create an image and save it wherever the user
 * decides.
 * 
 * @author Johann Hofmann
 * @author Gregor Rosenbaum
 * @author Anton Krebs
 */

public class ImageSaver {

	// uses ImageIO so save the BufferedImage to the path
	// the suffix is added without any checks if it is already specified in path
	// you could do some regex but that seemed like a bit overkill for a simple
	// application
	public static void saveImageToPng(final BufferedImage img, final String path)
			throws IOException {
		ImageIO.write(img, "png", new File(path + ".png"));
	}

	// same as above, with a file instead of a path
	public static void saveImageToPng(final BufferedImage img, final File file)
			throws IOException {
		saveImageToPng(img, file.getPath());
	}

	public static void main(String[] args) {

		// our application window's height and width
		final int WIDTH = 640;
		final int HEIGHT = 480;

		// our application window
		final JFrame myFrame = new JFrame("Image Saver");
		myFrame.setSize(WIDTH, HEIGHT);

		// AK 1 Abbildung 5: Ebene
//		Geometry[] objects = new Geometry[] { new Plane(new Color(0, 1, 0),
//				new Point3(0, -1, 0), new Normal3(0, 1, 0)) };
//		PerspectiveCamera testCam = new PerspectiveCamera(new Point3(0, 0, 0),
//				new Vector3(0, 0, -1), new Vector3(0, 1, 0), Math.PI / 4);

		// AK 2 Abbildung 6: Kugel
//		 Geometry[] objects = new Geometry[] { new Sphere(new Color(1, 0, 0),
//		 new Point3(0, 0,
//		 -3), 0.5)};
//		 PerspectiveCamera testCam = new PerspectiveCamera(new Point3(0, 0,
//		 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), Math.PI / 4);

		// AK 3 Abbildung 7: Box
		 Geometry[] objects = new Geometry[] { new AxisAlignedBox(new Color(0,
		 0, 1),new Point3(-0.5, 0, -0.5),new Point3(0.5, 1,
		 0.5)) };
		 PerspectiveCamera testCam = new PerspectiveCamera(new Point3(3, 3,
		 3), new Vector3(-3, -3, -3), new Vector3(0, 1, 0), Math.PI / 4);

		// AK 8 Abbildung 8: Dreieck
//		 Geometry[] objects = new Geometry[] { new Triangle(new Color (1, 0,
//		 1), new Point3(-0.5, 0.5, -3), new Point3 (0.5, 0.5, -3), new Point3
//		 (0.5, -0.5, -3))};
//		 PerspectiveCamera testCam = new PerspectiveCamera(new Point3(0, 0,
//		 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), Math.PI / 4);

		// AK 9 Abbildung 9: Zwei Kugeln perspektivisch.
//		 Geometry[] objects = new Geometry[] { new Sphere(new Color(1, 0, 0),
//		 new Point3(-1, 0, -3), 0.5),
//		 new Sphere(new Color(1, 0, 0), new Point3(1, 0, -6), 0.5) };
//		 PerspectiveCamera testCam = new PerspectiveCamera(new Point3(0, 0,
//		 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), Math.PI / 4);

		// AK 10 Abbildung 10: Zwei Kugeln orthographisch.
//		 Geometry[] objects = new Geometry[] { new Sphere(new Color(1, 0, 0),
//		 new Point3(-1, 0, -3), 0.5),
//		 new Sphere(new Color(1, 0, 0), new Point3(1, 0, -6), 0.5) };
//		 OrthographicCamera testCam = new OrthographicCamera(new Point3(0, 0,
//		 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), 3);

		World testWorld = new World(new Color(0, 0, 0), objects);
		final RayTracer canvas = new RayTracer(WIDTH, HEIGHT, testWorld,
				testCam);
		myFrame.add(canvas);

		// we add a Listener to adjust the image when the user resizes the
		// window
		myFrame.addComponentListener(new ComponentListener() {

			@Override
			public void componentHidden(ComponentEvent e) {
				// not used but necessary for the Listener
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// not used but necessary for the Listener
			}

			@Override
			public void componentResized(ComponentEvent e) {
				// sets width and height of our image(canvas) to the dimensions
				// of the frame
				canvas.setWidth(myFrame.getWidth());
				canvas.setHeight(myFrame.getHeight());
			}

			@Override
			public void componentShown(ComponentEvent e) {
				// not used but necessary for the Listener
			}
		});

		// adds our Menu Datei->Speichern to save the file

		final JMenuBar menuBar = new JMenuBar();
		myFrame.setJMenuBar(menuBar);

		final JMenu file = new JMenu("Datei");
		menuBar.add(file);

		final JMenuItem saveFile = new JMenuItem("Speichern");
		file.add(saveFile);

		// the dialog to select the save destination
		final JFileChooser fileDialog = new JFileChooser();

		// listens if the menu item was clicked
		saveFile.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// not used but necessary for the Listener
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// not used but necessary for the Listener
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// not used but necessary for the Listener
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// not used but necessary for the Listener
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// if the filedialog returns that the user clicked "ok"
				if (fileDialog.showSaveDialog(myFrame) == JFileChooser.APPROVE_OPTION) {
					try {
						saveImageToPng(canvas.getImage(),
								fileDialog.getSelectedFile());
					} catch (IOException exception) {
						// messagebox alerts the user in case of an error
						JOptionPane
								.showMessageDialog(myFrame,
										"Fehler bei Dateiauswahl. Datei nicht gespeichert.");
					}
				}

			}
		});

		// shows the application
		myFrame.setVisible(true);

	}
}
