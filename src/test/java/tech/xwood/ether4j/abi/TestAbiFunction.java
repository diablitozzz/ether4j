package tech.xwood.ether4j.abi;

import java.math.BigInteger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestAbiFunction {

  @Test
  public void testDecodeResult() {

    Assert.assertEquals(
      AbiFunction.decodeResult(
        "0000000000000000000000000000000000000000000000000000000000000037",
        AbiUint.Type.of(256)),
      new AbiValue[] {
        AbiUint.of(256, 55)
      });

    Assert.assertEquals(
      AbiFunction.decodeResult(
        "00000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000"
          + "00000000000000000d6f6e65206d6f72652074696d6500000000000000000000000000000000000000",
        AbiString.Type.get()),
      new AbiValue[] {
        AbiString.of("one more time")
      });

    Assert.assertEquals(
      AbiFunction.decodeResult(
        "00000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000"
          + "000000000000000000",
        AbiString.Type.get()),
      new AbiValue[] {
        AbiString.of("")
      });

    Assert.assertEquals(
      AbiFunction.decodeResult(
        "00000000000000000000000000000000000000000000000000000000000000370000000000000000000000000000000000000000000000"
          + "000000000000000007",
        AbiUint.Type.of(256),
        AbiUint.Type.of(256)),
      new AbiValue[] {
        AbiUint.of(256, 55),
        AbiUint.of(256, 7)
      });

    Assert.assertEquals(
      AbiFunction.decodeResult(
        "00000000000000000000000000000000000000000000000000000000000000800000000000000000000000000000000000000000000000"
          + "0000000000000000c00000000000000000000000000000000000000000000000000000000000000100000000000000000000000000"
          + "0000000000000000000000000000000000000140000000000000000000000000000000000000000000000000000000000000000464"
          + "6566310000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"
          + "0000000000000000000467686931000000000000000000000000000000000000000000000000000000000000000000000000000000"
          + "0000000000000000000000000000000000000000046a6b6c3100000000000000000000000000000000000000000000000000000000"
          + "00000000000000000000000000000000000000000000000000000000000000046d6e6f320000000000000000000000000000000000"
          + "0000000000000000000000",
        AbiString.Type.get(),
        AbiString.Type.get(),
        AbiString.Type.get(),
        AbiString.Type.get()),
      new AbiValue[] {
        AbiString.of("def1"),
        AbiString.of("ghi1"),
        AbiString.of("jkl1"),
        AbiString.of("mno2")
      });

    Assert.assertEquals(
      AbiFunction.decodeResult(
        "00000000000000000000000000000000000000000000000000000000000000370000000000000000000000000000000000000000000000"
          + "000000000000000001000000000000000000000000000000000000000000000000000000000000000a",
        AbiArray.Type.of(AbiUint.Type.of(256), 2),
        AbiUint.Type.of(256)),
      new AbiValue[] {
        AbiArray.of(AbiUint.of(256, 55), AbiUint.of(256, 1)),
        AbiUint.of(256, 10)
      });

    Assert.assertEquals(
      AbiFunction.decodeResult(""),
      new AbiValue[0]);

    Assert.assertEquals(
      AbiFunction.decodeResult("", AbiUint.Type.of(256)),
      new AbiValue[0]);

  }

  @Test
  public void testEncodeArgs() {

    final String raw = "00000000000000000000000000000000000000000000000000000000000188940000000000000000000000000000000"
      + "000000000000000000000000000000040000000000000000000000000000000000000000000000000000000000000000f437573746f6d2"
      + "06c6f6720746578740000000000000000000000000000000000";

    final AbiValue[] args = {
      AbiUint.of(256, 100500),
      AbiString.of("Custom log text")
    };

    final StringBuilder dest = new StringBuilder();
    AbiFunction.encodeArgs(dest, args);
    Assert.assertEquals(dest.toString(), raw);
    final AbiValue[] decoded = AbiFunction.decodeResult(raw, new AbiType[] { AbiUint.Type.of(256), AbiString.Type.get() });
    Assert.assertEquals(args, decoded);
  }

  @Test
  public void testEncodeCall() {

    Assert.assertEquals(
      "cdcd77c000000000000000000000000000000000000000000000000000000000000000450000000000000000000000000000000000000000"
        + "000000000000000000000001",
      AbiFunction.encodeCall(
        "baz",
        AbiUint.of(32, 69),
        AbiBool.of(true)));

    Assert.assertEquals(
      "a5643bf200000000000000000000000000000000000000000000000000000000000000600000000000000000000000000000000000000000"
        + "00000000000000000000000100000000000000000000000000000000000000000000000000000000000000a000000000000000000000"
        + "000000000000000000000000000000000000000000046461766500000000000000000000000000000000000000000000000000000000"
        + "000000000000000000000000000000000000000000000000000000000000000300000000000000000000000000000000000000000000"
        + "000000000000000000010000000000000000000000000000000000000000000000000000000000000002000000000000000000000000"
        + "0000000000000000000000000000000000000003",
      AbiFunction.encodeCall(
        "sam",
        AbiBytesDynamic.of("dave".getBytes()),
        AbiBool.of(true),
        AbiArrayDynamic.of(
          AbiUint.of(256, 1),
          AbiUint.of(256, 2),
          AbiUint.of(256, 3))));

    Assert.assertEquals(
      "8be6524600000000000000000000000000000000000000000000000000000000000001230000000000000000000000000000000000000000"
        + "000000000000000000000080313233343536373839300000000000000000000000000000000000000000000000000000000000000000"
        + "000000000000000000000000000000000000000000e00000000000000000000000000000000000000000000000000000000000000002"
        + "000000000000000000000000000000000000000000000000000000000000045600000000000000000000000000000000000000000000"
        + "00000000000000000789000000000000000000000000000000000000000000000000000000000000000d48656c6c6f2c20776f726c64"
        + "2100000000000000000000000000000000000000",
      AbiFunction.encodeCall(
        "f",
        AbiUint.of(256, BigInteger.valueOf(0x123)),
        AbiArrayDynamic.of(
          AbiUint.of(32, BigInteger.valueOf(0x456)),
          AbiUint.of(32, BigInteger.valueOf(0x789))),
        AbiBytes.of("1234567890".getBytes()),
        AbiBytesDynamic.of("Hello, world!".getBytes())));
  }

  @Test
  public void testEncodeConstructorCall() {

    Assert.assertEquals("", AbiFunction.encodeConstructorCall());
    Assert.assertEquals(
      "0000000000000000000000000000000000000000000000000000000000000020000000000000000000000000000000000000000000000000"
        + "000000000000000a4772656574696e67732100000000000000000000000000000000000000000000",
      AbiFunction.encodeConstructorCall(AbiString.of("Greetings!")));

    Assert.assertEquals(
      "0000000000000000000000000000000000000000000000000000000000000001000000000000000000000000000000000000000000000000"
        + "0000000000000020",
      AbiFunction.encodeConstructorCall(AbiUint.of(256, 1), AbiUint.of(256, BigInteger.valueOf(0x20))));

  }

  @Test
  public void testGetId() {
    Assert.assertEquals("cdcd77c0", AbiFunction.getId("baz", AbiUint.Type.of(32), AbiBool.Type.get()));
  }

  @Test
  public void testGetSignature() {
    Assert.assertEquals("baz(uint32,bool)", AbiFunction.getSignature("baz", AbiUint.Type.of(32), AbiBool.Type.get()));
    Assert.assertEquals("empty()", AbiFunction.getSignature("empty"));
  }
}