package org.ripple.power.collection;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UTFDataFormatException;

import org.ripple.power.Closed;
import org.ripple.power.utils.MathUtils;

public class ArrayByte implements Closed{

	public static final int BIG_ENDIAN = 0;

	public static final int LITTLE_ENDIAN = 1;

	public int type;

	private byte[] data;

	private int position;

	private int byteOrder;

	public ArrayByte() {
		this(1024 * 10);
	}

	public ArrayByte(int length) {
		this(new byte[length]);
	}

	public ArrayByte(InputStream ins, int type) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024 * 10];
		int n = 0;
		while (-1 != (n = ins.read(buffer))) {
			output.write(buffer, 0, n);
		}
		this.data = output.toByteArray();
		reset(type);
		if (ins != null) {
			ins.close();
			ins = null;
		}
		output = null;
	}

	public ArrayByte(byte[] data) {
		this.data = data;
		reset();
	}

	public void reset() {
		reset(BIG_ENDIAN);
	}

	public void reset(int type) {
		position = 0;
		byteOrder = type;
	}

	public byte[] getData() {
		return data;
	}

	public int getByteOrder() {
		return byteOrder;
	}

	public void setByteOrder(int byteOrder) {
		this.byteOrder = byteOrder;
	}

	public byte[] readByteArray(int readLength) throws Exception {
		byte[] readBytes = new byte[readLength];
		read(readBytes);
		return readBytes;
	}

	public int length() {
		return data.length;
	}

	public void setLength(int length) {
		if (length != data.length) {
			byte[] oldData = data;
			data = new byte[length];
			System.arraycopy(oldData, 0, data, 0,
					Math.min(oldData.length, length));
			if (position > length) {
				position = length;
			}
		}
	}

	public int position() {
		return position;
	}

	public void setPosition(int position) throws IndexOutOfBoundsException {
		if (position < 0 || position > data.length) {
			throw new IndexOutOfBoundsException();
		}

		this.position = position;
	}

	public void truncate() {
		setLength(position);
	}

	public int available() {
		return length() - position();
	}

	private void checkAvailable(int length) throws IndexOutOfBoundsException {
		if (available() < length) {
			throw new IndexOutOfBoundsException();
		}
	}

	public byte readByte() throws IndexOutOfBoundsException {
		checkAvailable(1);
		return data[position++];
	}

	public int read(byte[] buffer) throws IndexOutOfBoundsException {
		return read(buffer, 0, buffer.length);
	}

	public int read(byte[] buffer, int offset, int length)
			throws IndexOutOfBoundsException {
		if (length == 0) {
			return 0;
		}
		checkAvailable(length);
		System.arraycopy(data, position, buffer, offset, length);
		position += length;
		return length;
	}

	public long skip(long n) throws IOException {
		long remaining = n;
		int nr;
		if (n <= 0) {
			return 0;
		}
		int size = (int) MathUtils.min(2048, remaining);
		byte[] skipBuffer = new byte[size];
		while (remaining > 0) {
			nr = read(skipBuffer, 0, (int) Math.min(size, remaining));
			if (nr < 0) {
				break;
			}
			remaining -= nr;
		}
		return n - remaining;
	}

	public void read(OutputStream out) throws IOException {
		out.write(data, position, data.length - position);
		position = data.length;
	}

	public boolean readBoolean() throws IndexOutOfBoundsException {
		return (readByte() != 0);
	}

	public short readShort() throws IndexOutOfBoundsException {
		checkAvailable(2);
		if (byteOrder == type) {
			return (short) ((data[position++] & 0xff) | ((data[position++] & 0xff) << 8));
		} else {
			return (short) (((data[position++] & 0xff) << 8) | (data[position++] & 0xff));
		}
	}

	public int readInt() throws IndexOutOfBoundsException {
		checkAvailable(4);
		if (byteOrder == type) {
			return (data[position++] & 0xff) | ((data[position++] & 0xff) << 8)
					| ((data[position++] & 0xff) << 16)
					| ((data[position++] & 0xff) << 24);
		} else {
			return ((data[position++] & 0xff) << 24)
					| ((data[position++] & 0xff) << 16)
					| ((data[position++] & 0xff) << 8)
					| (data[position++] & 0xff);
		}
	}

	public long readLong() throws IndexOutOfBoundsException {
		checkAvailable(8);
		if (byteOrder == type) {
			return (readInt() & 0xffffffffL)
					| ((readInt() & 0xffffffffL) << 32L);
		} else {
			return ((readInt() & 0xffffffffL) << 32L)
					| (readInt() & 0xffffffffL);
		}
	}

	public float readFloat() throws IndexOutOfBoundsException {
		return Float.intBitsToFloat(readInt());
	}

	public double readDouble() throws IndexOutOfBoundsException {
		return Double.longBitsToDouble(readLong());
	}

	public String readUTF() throws IndexOutOfBoundsException,
			UTFDataFormatException {
		checkAvailable(2);
		int utfLength = readShort() & 0xffff;
		checkAvailable(utfLength);

		int goalPosition = position() + utfLength;

		StringBuffer string = new StringBuffer(utfLength);
		while (position() < goalPosition) {
			int a = readByte() & 0xff;
			if ((a & 0x80) == 0) {
				string.append((char) a);
			} else {
				int b = readByte() & 0xff;
				if ((b & 0xc0) != 0x80) {
					throw new UTFDataFormatException();
				}

				if ((a & 0xe0) == 0xc0) {
					char ch = (char) (((a & 0x1f) << 6) | (b & 0x3f));
					string.append(ch);
				} else if ((a & 0xf0) == 0xe0) {
					int c = readByte() & 0xff;
					if ((c & 0xc0) != 0x80) {
						throw new UTFDataFormatException();
					}
					char ch = (char) (((a & 0x0f) << 12) | ((b & 0x3f) << 6) | (c & 0x3f));
					string.append(ch);
				} else {
					throw new UTFDataFormatException();
				}
			}
		}
		return string.toString();
	}

	private void ensureCapacity(int dataSize) {
		if (position + dataSize > data.length) {
			setLength(position + dataSize);
		}
	}

	public void writeByte(int v) {
		ensureCapacity(1);
		data[position++] = (byte) v;
	}

	public void write(byte[] buffer) {
		write(buffer, 0, buffer.length);
	}

	public void write(byte[] buffer, int offset, int length) {
		if (length == 0) {
			return;
		}
		ensureCapacity(length);
		System.arraycopy(buffer, offset, data, position, length);
		position += length;
	}

	public void write(InputStream in) throws IOException {
		write(in, 8192);
	}

	public void write(InputStream in, int size) throws IOException {
		byte[] buffer = new byte[size];
		while (true) {
			int bytesRead = in.read(buffer);
			if (bytesRead == -1) {
				return;
			}
			write(buffer, 0, bytesRead);
		}
	}

	public void writeBoolean(boolean v) {
		writeByte(v ? -1 : 0);
	}

	public void writeShort(int v) {
		ensureCapacity(2);
		if (byteOrder == type) {
			data[position++] = (byte) (v & 0xff);
			data[position++] = (byte) ((v >> 8) & 0xff);
		} else {
			data[position++] = (byte) ((v >> 8) & 0xff);
			data[position++] = (byte) (v & 0xff);
		}
	}

	public void writeInt(int v) {
		ensureCapacity(4);
		if (byteOrder == type) {
			data[position++] = (byte) (v & 0xff);
			data[position++] = (byte) ((v >> 8) & 0xff);
			data[position++] = (byte) ((v >> 16) & 0xff);
			data[position++] = (byte) (v >>> 24);
		} else {
			data[position++] = (byte) (v >>> 24);
			data[position++] = (byte) ((v >> 16) & 0xff);
			data[position++] = (byte) ((v >> 8) & 0xff);
			data[position++] = (byte) (v & 0xff);
		}
	}

	public void writeLong(long v) {
		ensureCapacity(8);
		if (byteOrder == type) {
			writeInt((int) (v & 0xffffffffL));
			writeInt((int) (v >>> 32));
		} else {
			writeInt((int) (v >>> 32));
			writeInt((int) (v & 0xffffffffL));
		}
	}

	public void writeFloat(float v) {
		writeInt(Float.floatToIntBits(v));
	}

	public void writeDouble(double v) {
		writeLong(Double.doubleToLongBits(v));
	}

	public void writeUTF(String s) throws UTFDataFormatException {

		int utfLength = 0;
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch > 0 && ch < 0x80) {
				utfLength++;
			} else if (ch == 0 || (ch >= 0x80 && ch < 0x800)) {
				utfLength += 2;
			} else {
				utfLength += 3;
			}
		}

		if (utfLength > 65535) {
			throw new UTFDataFormatException();
		}

		ensureCapacity(2 + utfLength);
		writeShort(utfLength);

		for (int i = 0; i < s.length(); i++) {
			int ch = s.charAt(i);
			if (ch > 0 && ch < 0x80) {
				writeByte(ch);
			} else if (ch == 0 || (ch >= 0x80 && ch < 0x800)) {
				writeByte(0xc0 | (0x1f & (ch >> 6)));
				writeByte(0x80 | (0x3f & ch));
			} else {
				writeByte(0xe0 | (0x0f & (ch >> 12)));
				writeByte(0x80 | (0x3f & (ch >> 6)));
				writeByte(0x80 | (0x3f & ch));
			}
		}
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public void close() {
		this.data = null;
	}

}
