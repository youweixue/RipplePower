package org.spongycastle.crypto.tls;

/**
 * RFC 2246 A.5
 */
public class CipherSuite {
	public static final int TLS_NULL_WITH_NULL_NULL = 0x0000;
	public static final int TLS_RSA_WITH_NULL_MD5 = 0x0001;
	public static final int TLS_RSA_WITH_NULL_SHA = 0x0002;
	public static final int TLS_RSA_EXPORT_WITH_RC4_40_MD5 = 0x0003;
	public static final int TLS_RSA_WITH_RC4_128_MD5 = 0x0004;
	public static final int TLS_RSA_WITH_RC4_128_SHA = 0x0005;
	public static final int TLS_RSA_EXPORT_WITH_RC2_CBC_40_MD5 = 0x0006;
	public static final int TLS_RSA_WITH_IDEA_CBC_SHA = 0x0007;
	public static final int TLS_RSA_EXPORT_WITH_DES40_CBC_SHA = 0x0008;
	public static final int TLS_RSA_WITH_DES_CBC_SHA = 0x0009;
	public static final int TLS_RSA_WITH_3DES_EDE_CBC_SHA = 0x000A;
	public static final int TLS_DH_DSS_EXPORT_WITH_DES40_CBC_SHA = 0x000B;
	public static final int TLS_DH_DSS_WITH_DES_CBC_SHA = 0x000C;
	public static final int TLS_DH_DSS_WITH_3DES_EDE_CBC_SHA = 0x000D;
	public static final int TLS_DH_RSA_EXPORT_WITH_DES40_CBC_SHA = 0x000E;
	public static final int TLS_DH_RSA_WITH_DES_CBC_SHA = 0x000F;
	public static final int TLS_DH_RSA_WITH_3DES_EDE_CBC_SHA = 0x0010;
	public static final int TLS_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA = 0x0011;
	public static final int TLS_DHE_DSS_WITH_DES_CBC_SHA = 0x0012;
	public static final int TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA = 0x0013;
	public static final int TLS_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA = 0x0014;
	public static final int TLS_DHE_RSA_WITH_DES_CBC_SHA = 0x0015;
	public static final int TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA = 0x0016;
	public static final int TLS_DH_anon_EXPORT_WITH_RC4_40_MD5 = 0x0017;
	public static final int TLS_DH_anon_WITH_RC4_128_MD5 = 0x0018;
	public static final int TLS_DH_anon_EXPORT_WITH_DES40_CBC_SHA = 0x0019;
	public static final int TLS_DH_anon_WITH_DES_CBC_SHA = 0x001A;
	public static final int TLS_DH_anon_WITH_3DES_EDE_CBC_SHA = 0x001B;

	/*
	 * Note: The cipher suite values { 0x00, 0x1C } and { 0x00, 0x1D } are
	 * reserved to avoid collision with Fortezza-based cipher suites in SSL 3.
	 */

	/*
	 * RFC 3268
	 */
	public static final int TLS_RSA_WITH_AES_128_CBC_SHA = 0x002F;
	public static final int TLS_DH_DSS_WITH_AES_128_CBC_SHA = 0x0030;
	public static final int TLS_DH_RSA_WITH_AES_128_CBC_SHA = 0x0031;
	public static final int TLS_DHE_DSS_WITH_AES_128_CBC_SHA = 0x0032;
	public static final int TLS_DHE_RSA_WITH_AES_128_CBC_SHA = 0x0033;
	public static final int TLS_DH_anon_WITH_AES_128_CBC_SHA = 0x0034;
	public static final int TLS_RSA_WITH_AES_256_CBC_SHA = 0x0035;
	public static final int TLS_DH_DSS_WITH_AES_256_CBC_SHA = 0x0036;
	public static final int TLS_DH_RSA_WITH_AES_256_CBC_SHA = 0x0037;
	public static final int TLS_DHE_DSS_WITH_AES_256_CBC_SHA = 0x0038;
	public static final int TLS_DHE_RSA_WITH_AES_256_CBC_SHA = 0x0039;
	public static final int TLS_DH_anon_WITH_AES_256_CBC_SHA = 0x003A;

	/*
	 * RFC 4132
	 */
	public static final int TLS_RSA_WITH_CAMELLIA_128_CBC_SHA = 0x0041;
	public static final int TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA = 0x0042;
	public static final int TLS_DH_RSA_WITH_CAMELLIA_128_CBC_SHA = 0x0043;
	public static final int TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA = 0x0044;
	public static final int TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA = 0x0045;
	public static final int TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA = 0x0046;
	public static final int TLS_RSA_WITH_CAMELLIA_256_CBC_SHA = 0x0084;
	public static final int TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA = 0x0085;
	public static final int TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA = 0x0086;
	public static final int TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA = 0x0087;
	public static final int TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA = 0x0088;
	public static final int TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA = 0x0089;

	/*
	 * RFC 4162
	 */
	public static final int TLS_RSA_WITH_SEED_CBC_SHA = 0x0096;
	public static final int TLS_DH_DSS_WITH_SEED_CBC_SHA = 0x0097;
	public static final int TLS_DH_RSA_WITH_SEED_CBC_SHA = 0x0098;
	public static final int TLS_DHE_DSS_WITH_SEED_CBC_SHA = 0x0099;
	public static final int TLS_DHE_RSA_WITH_SEED_CBC_SHA = 0x009A;
	public static final int TLS_DH_anon_WITH_SEED_CBC_SHA = 0x009B;

	/*
	 * RFC 4279
	 */
	public static final int TLS_PSK_WITH_RC4_128_SHA = 0x008A;
	public static final int TLS_PSK_WITH_3DES_EDE_CBC_SHA = 0x008B;
	public static final int TLS_PSK_WITH_AES_128_CBC_SHA = 0x008C;
	public static final int TLS_PSK_WITH_AES_256_CBC_SHA = 0x008D;
	public static final int TLS_DHE_PSK_WITH_RC4_128_SHA = 0x008E;
	public static final int TLS_DHE_PSK_WITH_3DES_EDE_CBC_SHA = 0x008F;
	public static final int TLS_DHE_PSK_WITH_AES_128_CBC_SHA = 0x0090;
	public static final int TLS_DHE_PSK_WITH_AES_256_CBC_SHA = 0x0091;
	public static final int TLS_RSA_PSK_WITH_RC4_128_SHA = 0x0092;
	public static final int TLS_RSA_PSK_WITH_3DES_EDE_CBC_SHA = 0x0093;
	public static final int TLS_RSA_PSK_WITH_AES_128_CBC_SHA = 0x0094;
	public static final int TLS_RSA_PSK_WITH_AES_256_CBC_SHA = 0x0095;

	/*
	 * RFC 4492
	 */
	public static final int TLS_ECDH_ECDSA_WITH_NULL_SHA = 0xC001;
	public static final int TLS_ECDH_ECDSA_WITH_RC4_128_SHA = 0xC002;
	public static final int TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA = 0xC003;
	public static final int TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA = 0xC004;
	public static final int TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA = 0xC005;
	public static final int TLS_ECDHE_ECDSA_WITH_NULL_SHA = 0xC006;
	public static final int TLS_ECDHE_ECDSA_WITH_RC4_128_SHA = 0xC007;
	public static final int TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA = 0xC008;
	public static final int TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA = 0xC009;
	public static final int TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA = 0xC00A;
	public static final int TLS_ECDH_RSA_WITH_NULL_SHA = 0xC00B;
	public static final int TLS_ECDH_RSA_WITH_RC4_128_SHA = 0xC00C;
	public static final int TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA = 0xC00D;
	public static final int TLS_ECDH_RSA_WITH_AES_128_CBC_SHA = 0xC00E;
	public static final int TLS_ECDH_RSA_WITH_AES_256_CBC_SHA = 0xC00F;
	public static final int TLS_ECDHE_RSA_WITH_NULL_SHA = 0xC010;
	public static final int TLS_ECDHE_RSA_WITH_RC4_128_SHA = 0xC011;
	public static final int TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA = 0xC012;
	public static final int TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA = 0xC013;
	public static final int TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA = 0xC014;
	public static final int TLS_ECDH_anon_WITH_NULL_SHA = 0xC015;
	public static final int TLS_ECDH_anon_WITH_RC4_128_SHA = 0xC016;
	public static final int TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA = 0xC017;
	public static final int TLS_ECDH_anon_WITH_AES_128_CBC_SHA = 0xC018;
	public static final int TLS_ECDH_anon_WITH_AES_256_CBC_SHA = 0xC019;

	/*
	 * RFC 4785
	 */
	public static final int TLS_PSK_WITH_NULL_SHA = 0x002C;
	public static final int TLS_DHE_PSK_WITH_NULL_SHA = 0x002D;
	public static final int TLS_RSA_PSK_WITH_NULL_SHA = 0x002E;

	/*
	 * RFC 5054
	 */
	public static final int TLS_SRP_SHA_WITH_3DES_EDE_CBC_SHA = 0xC01A;
	public static final int TLS_SRP_SHA_RSA_WITH_3DES_EDE_CBC_SHA = 0xC01B;
	public static final int TLS_SRP_SHA_DSS_WITH_3DES_EDE_CBC_SHA = 0xC01C;
	public static final int TLS_SRP_SHA_WITH_AES_128_CBC_SHA = 0xC01D;
	public static final int TLS_SRP_SHA_RSA_WITH_AES_128_CBC_SHA = 0xC01E;
	public static final int TLS_SRP_SHA_DSS_WITH_AES_128_CBC_SHA = 0xC01F;
	public static final int TLS_SRP_SHA_WITH_AES_256_CBC_SHA = 0xC020;
	public static final int TLS_SRP_SHA_RSA_WITH_AES_256_CBC_SHA = 0xC021;
	public static final int TLS_SRP_SHA_DSS_WITH_AES_256_CBC_SHA = 0xC022;

	/*
	 * RFC 5246
	 */
	public static final int TLS_RSA_WITH_NULL_SHA256 = 0x003B;
	public static final int TLS_RSA_WITH_AES_128_CBC_SHA256 = 0x003C;
	public static final int TLS_RSA_WITH_AES_256_CBC_SHA256 = 0x003D;
	public static final int TLS_DH_DSS_WITH_AES_128_CBC_SHA256 = 0x003E;
	public static final int TLS_DH_RSA_WITH_AES_128_CBC_SHA256 = 0x003F;
	public static final int TLS_DHE_DSS_WITH_AES_128_CBC_SHA256 = 0x0040;
	public static final int TLS_DHE_RSA_WITH_AES_128_CBC_SHA256 = 0x0067;
	public static final int TLS_DH_DSS_WITH_AES_256_CBC_SHA256 = 0x0068;
	public static final int TLS_DH_RSA_WITH_AES_256_CBC_SHA256 = 0x0069;
	public static final int TLS_DHE_DSS_WITH_AES_256_CBC_SHA256 = 0x006A;
	public static final int TLS_DHE_RSA_WITH_AES_256_CBC_SHA256 = 0x006B;
	public static final int TLS_DH_anon_WITH_AES_128_CBC_SHA256 = 0x006C;
	public static final int TLS_DH_anon_WITH_AES_256_CBC_SHA256 = 0x006D;

	/*
	 * RFC 5288
	 */
	public static final int TLS_RSA_WITH_AES_128_GCM_SHA256 = 0x009C;
	public static final int TLS_RSA_WITH_AES_256_GCM_SHA384 = 0x009D;
	public static final int TLS_DHE_RSA_WITH_AES_128_GCM_SHA256 = 0x009E;
	public static final int TLS_DHE_RSA_WITH_AES_256_GCM_SHA384 = 0x009F;
	public static final int TLS_DH_RSA_WITH_AES_128_GCM_SHA256 = 0x00A0;
	public static final int TLS_DH_RSA_WITH_AES_256_GCM_SHA384 = 0x00A1;
	public static final int TLS_DHE_DSS_WITH_AES_128_GCM_SHA256 = 0x00A2;
	public static final int TLS_DHE_DSS_WITH_AES_256_GCM_SHA384 = 0x00A3;
	public static final int TLS_DH_DSS_WITH_AES_128_GCM_SHA256 = 0x00A4;
	public static final int TLS_DH_DSS_WITH_AES_256_GCM_SHA384 = 0x00A5;
	public static final int TLS_DH_anon_WITH_AES_128_GCM_SHA256 = 0x00A6;
	public static final int TLS_DH_anon_WITH_AES_256_GCM_SHA384 = 0x00A7;

	/*
	 * RFC 5289
	 */
	public static final int TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256 = 0xC023;
	public static final int TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384 = 0xC024;
	public static final int TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256 = 0xC025;
	public static final int TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384 = 0xC026;
	public static final int TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256 = 0xC027;
	public static final int TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384 = 0xC028;
	public static final int TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256 = 0xC029;
	public static final int TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384 = 0xC02A;
	public static final int TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256 = 0xC02B;
	public static final int TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384 = 0xC02C;
	public static final int TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256 = 0xC02D;
	public static final int TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384 = 0xC02E;
	public static final int TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256 = 0xC02F;
	public static final int TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384 = 0xC030;
	public static final int TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256 = 0xC031;
	public static final int TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384 = 0xC032;

	/*
	 * RFC 5487
	 */
	public static final int TLS_PSK_WITH_AES_128_GCM_SHA256 = 0x00A8;
	public static final int TLS_PSK_WITH_AES_256_GCM_SHA384 = 0x00A9;
	public static final int TLS_DHE_PSK_WITH_AES_128_GCM_SHA256 = 0x00AA;
	public static final int TLS_DHE_PSK_WITH_AES_256_GCM_SHA384 = 0x00AB;
	public static final int TLS_RSA_PSK_WITH_AES_128_GCM_SHA256 = 0x00AC;
	public static final int TLS_RSA_PSK_WITH_AES_256_GCM_SHA384 = 0x00AD;
	public static final int TLS_PSK_WITH_AES_128_CBC_SHA256 = 0x00AE;
	public static final int TLS_PSK_WITH_AES_256_CBC_SHA384 = 0x00AF;
	public static final int TLS_PSK_WITH_NULL_SHA256 = 0x00B0;
	public static final int TLS_PSK_WITH_NULL_SHA384 = 0x00B1;
	public static final int TLS_DHE_PSK_WITH_AES_128_CBC_SHA256 = 0x00B2;
	public static final int TLS_DHE_PSK_WITH_AES_256_CBC_SHA384 = 0x00B3;
	public static final int TLS_DHE_PSK_WITH_NULL_SHA256 = 0x00B4;
	public static final int TLS_DHE_PSK_WITH_NULL_SHA384 = 0x00B5;
	public static final int TLS_RSA_PSK_WITH_AES_128_CBC_SHA256 = 0x00B6;
	public static final int TLS_RSA_PSK_WITH_AES_256_CBC_SHA384 = 0x00B7;
	public static final int TLS_RSA_PSK_WITH_NULL_SHA256 = 0x00B8;
	public static final int TLS_RSA_PSK_WITH_NULL_SHA384 = 0x00B9;

	/*
	 * RFC 5489
	 */
	public static final int TLS_ECDHE_PSK_WITH_RC4_128_SHA = 0xC033;
	public static final int TLS_ECDHE_PSK_WITH_3DES_EDE_CBC_SHA = 0xC034;
	public static final int TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA = 0xC035;
	public static final int TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA = 0xC036;
	public static final int TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA256 = 0xC037;
	public static final int TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA384 = 0xC038;
	public static final int TLS_ECDHE_PSK_WITH_NULL_SHA = 0xC039;
	public static final int TLS_ECDHE_PSK_WITH_NULL_SHA256 = 0xC03A;
	public static final int TLS_ECDHE_PSK_WITH_NULL_SHA384 = 0xC03B;

	/*
	 * RFC 5746
	 */
	public static final int TLS_EMPTY_RENEGOTIATION_INFO_SCSV = 0x00FF;

	/*
	 * RFC 6655
	 */
	public static final int TLS_RSA_WITH_AES_128_CCM = 0xC09C;
	public static final int TLS_RSA_WITH_AES_256_CCM = 0xC09D;
	public static final int TLS_DHE_RSA_WITH_AES_128_CCM = 0xC09E;
	public static final int TLS_DHE_RSA_WITH_AES_256_CCM = 0xC09F;
	public static final int TLS_RSA_WITH_AES_128_CCM_8 = 0xC0A0;
	public static final int TLS_RSA_WITH_AES_256_CCM_8 = 0xC0A1;
	public static final int TLS_DHE_RSA_WITH_AES_128_CCM_8 = 0xC0A2;
	public static final int TLS_DHE_RSA_WITH_AES_256_CCM_8 = 0xC0A3;
	public static final int TLS_PSK_WITH_AES_128_CCM = 0xC0A4;
	public static final int TLS_PSK_WITH_AES_256_CCM = 0xC0A5;
	public static final int TLS_DHE_PSK_WITH_AES_128_CCM = 0xC0A6;
	public static final int TLS_DHE_PSK_WITH_AES_256_CCM = 0xC0A7;
	public static final int TLS_PSK_WITH_AES_128_CCM_8 = 0xC0A8;
	public static final int TLS_PSK_WITH_AES_256_CCM_8 = 0xC0A9;
	public static final int TLS_PSK_DHE_WITH_AES_128_CCM_8 = 0xC0AA;
	public static final int TLS_PSK_DHE_WITH_AES_256_CCM_8 = 0xC0AB;

	/*
	 * TBD[draft-josefsson-salsa20-tls-02]
	 */
	static final int TLS_RSA_WITH_ESTREAM_SALSA20_SHA1 = 0xFF00;
	static final int TLS_RSA_WITH_SALSA20_SHA1 = 0xFF01;
	static final int TLS_DHE_RSA_WITH_ESTREAM_SALSA20_SHA1 = 0xFF02;
	static final int TLS_DHE_RSA_WITH_SALSA20_SHA1 = 0xFF03;
	static final int TLS_ECDHE_RSA_WITH_ESTREAM_SALSA20_SHA1 = 0xFF04;
	static final int TLS_ECDHE_RSA_WITH_SALSA20_SHA1 = 0xFF05;
	static final int TLS_ECDHE_ECDSA_WITH_ESTREAM_SALSA20_SHA1 = 0xFF06;
	static final int TLS_ECDHE_ECDSA_WITH_SALSA20_SHA1 = 0xFF07;
	static final int TLS_PSK_WITH_ESTREAM_SALSA20_SHA1 = 0xFF08;
	static final int TLS_PSK_WITH_SALSA20_SHA1 = 0xFF09;
	static final int TLS_DHE_PSK_WITH_ESTREAM_SALSA20_SHA1 = 0xFF0A;
	static final int TLS_DHE_PSK_WITH_SALSA20_SHA1 = 0xFF0B;
	static final int TLS_RSA_PSK_WITH_ESTREAM_SALSA20_SHA1 = 0xFF0C;
	static final int TLS_RSA_PSK_WITH_SALSA20_SHA1 = 0xFF0D;
	static final int TLS_ECDHE_PSK_WITH_ESTREAM_SALSA20_SHA1 = 0xFF0E;
	static final int TLS_ECDHE_PSK_WITH_SALSA20_SHA1 = 0xFF0F;
	static final int TLS_RSA_WITH_ESTREAM_SALSA20_UMAC96 = 0xFF10;
	static final int TLS_RSA_WITH_SALSA20_UMAC96 = 0xFF11;
	static final int TLS_DHE_RSA_WITH_ESTREAM_SALSA20_UMAC96 = 0xFF12;
	static final int TLS_DHE_RSA_WITH_SALSA20_UMAC96 = 0xFF13;
	static final int TLS_ECDHE_RSA_WITH_ESTREAM_SALSA20_UMAC96 = 0xFF14;
	static final int TLS_ECDHE_RSA_WITH_SALSA20_UMAC96 = 0xFF15;
	static final int TLS_ECDHE_ECDSA_WITH_ESTREAM_SALSA20_UMAC96 = 0xFF16;
	static final int TLS_ECDHE_ECDSA_WITH_SALSA20_UMAC96 = 0xFF17;
	static final int TLS_PSK_WITH_ESTREAM_SALSA20_UMAC96 = 0xFF18;
	static final int TLS_PSK_WITH_SALSA20_UMAC96 = 0xFF19;
	static final int TLS_DHE_PSK_WITH_ESTREAM_SALSA20_UMAC96 = 0xFF1A;
	static final int TLS_DHE_PSK_WITH_SALSA20_UMAC96 = 0xFF1B;
	static final int TLS_RSA_PSK_WITH_ESTREAM_SALSA20_UMAC96 = 0xFF1C;
	static final int TLS_RSA_PSK_WITH_SALSA20_UMAC96 = 0xFF1D;
	static final int TLS_ECDHE_PSK_WITH_ESTREAM_SALSA20_UMAC96 = 0xFF1E;
	static final int TLS_ECDHE_PSK_WITH_SALSA20_UMAC96 = 0xFF1F;
}
