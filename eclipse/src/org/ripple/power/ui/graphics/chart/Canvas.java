package org.ripple.power.ui.graphics.chart;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

import org.ripple.power.ui.graphics.LColor;
import org.ripple.power.ui.graphics.LGraphics;
import org.ripple.power.ui.graphics.LImage;
import org.ripple.power.utils.GraphicsUtils;

class Canvas {
	private static final String UNKNOWN_STYLE = "unknown style: ";

	LImage _bufferedImage;
	LGraphics _graphics;

	Canvas() {

	}

	Canvas(Bitmap g) {
		this._graphics = g.bufferedImage.getLGraphics();
		enableAntiAliasing();
		_graphics.save();
	}

	Canvas(LGraphics g) {
		this._graphics = g;
		enableAntiAliasing();
		_graphics.save();
	}

	public void restore() {
		_graphics.restore();
	}

	public void save() {
		_graphics.save();
	}

	public void drawBitmap(Bitmap bitmap, int left, int top, Paint p) {
		if (p != null) {
			setColorAndStroke(p);
		}
		this._graphics.drawImage(
				JavaSEGraphicFactory.getBufferedImage(bitmap), left, top);
	}

	public void drawBitmap(Bitmap bitmap, int left, int top) {
		this._graphics.drawImage(
				JavaSEGraphicFactory.getBufferedImage(bitmap), left, top);
	}

	public void drawBitmap(Bitmap bitmap, Matrix matrix) {
		this._graphics.drawRenderedImage(JavaSEGraphicFactory
				.getBufferedImage(bitmap).getBufferedImage(),
				JavaSEGraphicFactory.getAffineTransform(matrix));
	}

	public void drawCircle(int x, int y, int radius, Paint paint) {
		if (paint.isTransparent()) {
			return;
		}

		Paint awtPaint = JavaSEGraphicFactory.getAwtPaint(paint);
		setColorAndStroke(awtPaint);
		int doubleRadius = radius * 2;

		Style style = awtPaint.style;
		switch (style) {
		case FILL:
			this._graphics.fillOval(x - radius, y - radius, doubleRadius,
					doubleRadius);
			return;

		case STROKE:
			this._graphics.drawOval(x - radius, y - radius, doubleRadius,
					doubleRadius);
			return;
		}

		throw new IllegalArgumentException(UNKNOWN_STYLE + style);
	}

	public void drawClear() {
		_graphics.drawClear();
	}

	public void drawRect(float x, float y, float w, float h, Paint paint) {
		if (paint.isTransparent()) {
			return;
		}

		setColorAndStroke(paint);

		Style style = paint.style;

		x = Math.abs(x - w);

		y = Math.abs(y - h);

		switch (style) {
		case FILL:
			this._graphics.fill(new Rectangle2D.Float(w, h, x, y));
			return;
		case STROKE:
			this._graphics.draw(new Rectangle2D.Float(w, h, x, y));
			return;
		}

		throw new IllegalArgumentException(UNKNOWN_STYLE + style);
	}

	public void drawLine(int x1, int y1, int x2, int y2, Paint paint) {
		if (paint.isTransparent()) {
			return;
		}

		setColorAndStroke(JavaSEGraphicFactory.getAwtPaint(paint));
		this._graphics.drawLine(x1, y1, x2, y2);
	}

	public void drawPath(Path path, Paint paint) {
		if (paint.isTransparent()) {
			return;
		}

		Paint awtPaint = JavaSEGraphicFactory.getAwtPaint(paint);
		Path awtPath = JavaSEGraphicFactory.getAwtPath(path);

		setColorAndStroke(awtPaint);
		this._graphics.setPaint(awtPaint.texturePaint);

		Style style = awtPaint.style;
		switch (style) {
		case FILL:
			this._graphics.fill(awtPath.path2D);
			return;

		case STROKE:
			this._graphics.draw(awtPath.path2D);
			return;
		}

		throw new IllegalArgumentException(UNKNOWN_STYLE + style);
	}

	public void drawPointTextContainer(PointTextContainer ptc, int maxWidth) {
		if (ptc.paintFront.isTransparent()
				&& (ptc.paintBack == null || ptc.paintBack.isTransparent())) {
			return;
		}
		int textWidth = ptc.paintFront.getTextWidth(ptc.text);
		if (textWidth > maxWidth) {
			AttributedString attrString = new AttributedString(ptc.text);
			Paint awtPaintFront = JavaSEGraphicFactory
					.getAwtPaint(ptc.paintFront);
			attrString.addAttribute(TextAttribute.FOREGROUND,
					awtPaintFront.color);
			attrString.addAttribute(TextAttribute.FONT, awtPaintFront.font);
			AttributedCharacterIterator paragraph = attrString.getIterator();
			int paragraphStart = paragraph.getBeginIndex();
			int paragraphEnd = paragraph.getEndIndex();
			FontRenderContext frc = this._graphics.getFontRenderContext();
			LineBreakMeasurer lineMeasurer = new LineBreakMeasurer(paragraph,
					frc);
			float layoutHeight = 0;
			lineMeasurer.setPosition(paragraphStart);
			while (lineMeasurer.getPosition() < paragraphEnd) {
				TextLayout layout = lineMeasurer.nextLayout(maxWidth);
				layoutHeight += layout.getAscent() + layout.getDescent()
						+ layout.getLeading();
			}
			float drawPosY = (float) ptc.y;
			lineMeasurer.setPosition(paragraphStart);
			while (lineMeasurer.getPosition() < paragraphEnd) {
				TextLayout layout = lineMeasurer.nextLayout(maxWidth);
				float posX = (float) ptc.x;
				float posY = drawPosY;
				if (Position.CENTER == ptc.position) {
					posX += (maxWidth - layout.getAdvance()) * 0.5f;
					posY += (layout.getAscent() + layout.getDescent()
							+ layout.getLeading() - layoutHeight) * 0.5f;
				} else if (Position.BELOW == ptc.position) {
					posX += (maxWidth - layout.getAdvance()) * 0.5f;
				} else if (Position.ABOVE == ptc.position) {
					posX += (maxWidth - layout.getAdvance()) * 0.5f;
					posY += layout.getAscent() + layout.getDescent()
							+ layout.getLeading() - layoutHeight;
				} else if (Position.LEFT == ptc.position) {
					posX += textWidth * 0.5f - maxWidth * 0.5f + maxWidth
							- layout.getAdvance();
					posY += (layout.getAscent() + layout.getDescent()
							+ layout.getLeading() - layoutHeight) * 0.5f;
				} else if (Position.RIGHT == ptc.position) {
					posX += -textWidth * 0.5f + maxWidth * 0.5f;
					posY += (layout.getAscent() + layout.getDescent()
							+ layout.getLeading() - layoutHeight) * 0.5f;
				} else {
					throw new IllegalArgumentException(
							"No position for drawing PointTextContainer");
				}
				if (ptc.paintBack != null) {
					setColorAndStroke(JavaSEGraphicFactory
							.getAwtPaint(ptc.paintBack));
					AffineTransform affineTransform = new AffineTransform();
					affineTransform.translate(posX, posY);
					this._graphics.draw(layout.getOutline(affineTransform));
				}
				layout.draw(this._graphics, posX, posY);
				drawPosY += layout.getAscent() + layout.getDescent()
						+ layout.getLeading();
			}
		} else {
			if (ptc.paintBack != null) {
				drawText(ptc.text, (int) ptc.x, (int) ptc.y, ptc.paintBack);
			}
			drawText(ptc.text, (int) ptc.x, (int) ptc.y, ptc.paintFront);
		}
	}

	public void drawText(String text, float x, float y, Paint paint) {
		if (paint.isTransparent()) {
			return;
		}

		Paint awtPaint = JavaSEGraphicFactory.getAwtPaint(paint);

		if (awtPaint.stroke == null) {
			this._graphics.setColor(awtPaint.color);
			this._graphics.setFont(awtPaint.font);
			if (paint._align != null) {

				switch (paint._align) {
				case LEFT:
					this._graphics.drawString(text, x, y, LGraphics.LEFT);
					break;
				case CENTER:
					this._graphics.drawString(text, x, y, LGraphics.HCENTER);
					break;
				case RIGHT:
					this._graphics.drawString(text, x, y, LGraphics.RIGHT);
					break;
				}

			} else {
				this._graphics.drawString(text, x, y);
			}
		} else {
			setColorAndStroke(awtPaint);
			TextLayout textLayout = new TextLayout(text, awtPaint.font,
					this._graphics.getFontRenderContext());
			AffineTransform affineTransform = new AffineTransform();
			affineTransform.translate(x, y);
			this._graphics.draw(textLayout.getOutline(affineTransform));
		}
	}

	public void drawTextRotated(String text, int x1, int y1, int x2, int y2,
			Paint paint) {
		if (paint.isTransparent()) {
			return;
		}

		AffineTransform affineTransform = this._graphics.getTransform();

		double theta = Math.atan2(y2 - y1, x2 - x1);
		this._graphics.rotate(theta, x1, y1);

		double lineLength = Math.hypot(x2 - x1, y2 - y1);
		int textWidth = paint.getTextWidth(text);
		int dx = (int) (lineLength - textWidth) / 2;
		int xy = paint.getTextHeight(text) / 3;
		drawText(text, x1 + dx, y1 + xy, paint);

		this._graphics.setTransform(affineTransform);
	}

	public void fillColor(LColor color) {
		fillColor(color.getARGB());
	}

	public void fillColor(int color) {
		fillColor(new java.awt.Color(color));
	}

	public Dimension getDimension() {
		return new Dimension(getWidth(), getHeight());
	}

	public int getHeight() {
		return this._bufferedImage != null ? this._bufferedImage.getHeight()
				: 0;
	}

	public int getWidth() {
		return this._bufferedImage != null ? this._bufferedImage.getWidth() : 0;
	}

	public void resetClip() {
		this._graphics.setClip(null);
	}

	public void setBitmap(Bitmap bitmap) {
		if (bitmap == null) {
			this._bufferedImage = null;
			this._graphics = null;
		} else {
			this._bufferedImage = JavaSEGraphicFactory.getBufferedImage(bitmap);
			this._graphics = this._bufferedImage.getLGraphics();
			enableAntiAliasing();
			this._graphics.setRenderingHint(RenderingHints.KEY_RENDERING,
					RenderingHints.VALUE_RENDER_QUALITY);
			this._graphics.setRenderingHint(
					RenderingHints.KEY_STROKE_CONTROL,
					RenderingHints.VALUE_STROKE_PURE);
		}
	}

	public void setClip(int left, int top, int width, int height) {
		this._graphics.setClip(left, top, width, height);
	}

	private void enableAntiAliasing() {
		GraphicsUtils.setExcellentRenderingHints(_graphics);
	}

	private void fillColor(java.awt.Color color) {
		this._graphics.setComposite(AlphaComposite
				.getInstance(AlphaComposite.SRC));
		this._graphics.setColor(color);
		this._graphics.fillRect(0, 0, getWidth(), getHeight());
	}

	private void setColorAndStroke(Paint awtPaint) {
		this._graphics.setAntiAlias(awtPaint.isAntiAlias());
		this._graphics.setColor(awtPaint.color);
		if (awtPaint.stroke != null) {
			this._graphics.setStroke(awtPaint.stroke);
		}
	}
}
