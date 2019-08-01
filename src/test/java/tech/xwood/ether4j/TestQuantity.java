package tech.xwood.ether4j;

import java.security.SecureRandom;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestQuantity {

  private static final SecureRandom RANDOM = new SecureRandom();

  public static Quantity randomQuanity() {
    long next = 0;
    while ((next = RANDOM.nextLong()) <= 0) {
    }
    return Quantity.of(next);
  }

  @Test
  public void testEquals() {
    Assert.assertEquals(Quantity.of(0), Quantity.of(0));
    Assert.assertEquals(Quantity.of(0), Quantity.of("0x0"));
  }

  @Test
  public void testHex() {
    final String orig = "0x608060405234801561001057600080fd5b50604051610ada380380610ada83398101806040528101908080518201"
      + "929190602001805190602001909291905050506000816000819055506000600360006101000a81548163ffffffff021916908363ffffff"
      + "ff160217905550600090505b82518163ffffffff16101561021d576001606060405190810160405280600015158152602001858463ffff"
      + "ffff168151811015156100aa57fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff168152602001600063"
      + "ffffffff168152509080600181540180825580915050906001820390600052602060002001600090919290919091506000820151816000"
      + "0160006101000a81548160ff02191690831515021790555060208201518160000160016101000a81548173ffffffffffffffffffffffff"
      + "ffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060408201518160000160156101000a"
      + "81548163ffffffff021916908363ffffffff1602179055505050506001810160026000858463ffffffff168151811015156101ae57fe5b"
      + "9060200190602002015173ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681"
      + "5260200190815260200160002060006101000a81548163ffffffff021916908363ffffffff160217905550808060010191505061007056"
      + "5b5050506108ab8061022f6000396000f300608060405260043610610078576000357c0100000000000000000000000000000000000000"
      + "000000000000000000900463ffffffff1680633477ee2e1461007d5780636853a1da146101085780636dd7d8ea146101635780637b3529"
      + "62146101a65780638e7ea5b2146101d5578063da284dcc1461023f575b600080fd5b34801561008957600080fd5b506100a86004803603"
      + "810190808035906020019092919050505061026a565b60405180841515151581526020018373ffffffffffffffffffffffffffffffffff"
      + "ffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018263ffffffff1663ffffffff168152602001935050505060"
      + "405180910390f35b34801561011457600080fd5b50610149600480360381019080803573ffffffffffffffffffffffffffffffffffffff"
      + "ff1690602001909291905050506102dc565b604051808215151515815260200191505060405180910390f35b34801561016f57600080fd"
      + "5b506101a4600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061033d565b00"
      + "5b3480156101b257600080fd5b506101bb610688565b604051808215151515815260200191505060405180910390f35b3480156101e157"
      + "600080fd5b506101ea610694565b604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffff"
      + "ffffffffffffff1681526020018263ffffffff1663ffffffff1681526020019250505060405180910390f35b34801561024b57600080fd"
      + "5b5061025461081d565b6040518082815260200191505060405180910390f35b60018181548110151561027957fe5b9060005260206000"
      + "20016000915090508060000160009054906101000a900460ff16908060000160019054906101000a900473ffffffffffffffffffffffff"
      + "ffffffffffffffff16908060000160159054906101000a900463ffffffff16905083565b600080600260008473ffffffffffffffffffff"
      + "ffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000905490610100"
      + "0a900463ffffffff1663ffffffff16119050919050565b60008060008061034b610688565b1515156103c0576040517f08c379a0000000"
      + "0000000000000000000000000000000000000000000000000081526004018080602001828103825260128152602001807f566f74696e67"
      + "2069732066696e6973686564000000000000000000000000000081525060200191505060405180910390fd5b6103c9336102dc565b1515"
      + "61043d576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252"
      + "600f8152602001807f566f746572206e6f7420666f756e6400000000000000000000000000000000008152506020019150506040518091"
      + "0390fd5b610446856102dc565b15156104ba576040517f08c379a000000000000000000000000000000000000000000000000000000000"
      + "81526004018080602001828103825260138152602001807f43616e646964617465206e6f7420666f756e64000000000000000000000000"
      + "0081525060200191505060405180910390fd5b60016104c533610823565b63ffffffff168154811015156104d757fe5b90600052602060"
      + "00200193508360000160009054906101000a900460ff1615151561056a576040517f08c379a00000000000000000000000000000000000"
      + "000000000000000000000081526004018080602001828103825260138152602001807f566f74657220616c726561647920766f74656400"
      + "00000000000000000000000081525060200191505060405180910390fd5b61057385610823565b925060018363ffffffff168154811015"
      + "1561058a57fe5b90600052602060002001915060018460000160006101000a81548160ff02191690831515021790555081600001601581"
      + "819054906101000a900463ffffffff168092919060010191906101000a81548163ffffffff021916908363ffffffff1602179055505060"
      + "01600360009054906101000a900463ffffffff1663ffffffff1681548110151561061757fe5b9060005260206000200190508160000160"
      + "159054906101000a900463ffffffff1663ffffffff168160000160159054906101000a900463ffffffff1663ffffffff16101561068157"
      + "82600360006101000a81548163ffffffff021916908363ffffffff1602179055505b5050505050565b60004260005410905090565b6000"
      + "8060006106a1610688565b1515610715576040517f08c379a0000000000000000000000000000000000000000000000000000000008152"
      + "6004018080602001828103825260168152602001807f566f74696e67206973206e6f742066696e69736865640000000000000000000081"
      + "525060200191505060405180910390fd5b6001600360009054906101000a900463ffffffff1663ffffffff1681548110151561073c57fe"
      + "5b90600052602060002001905060008160000160159054906101000a900463ffffffff1663ffffffff161115156107da576040517f08c3"
      + "79a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600d8152602001807f4e"
      + "6f626f647920766f697465640000000000000000000000000000000000000081525060200191505060405180910390fd5b806000016001"
      + "9054906101000a900473ffffffffffffffffffffffffffffffffffffffff168160000160159054906101000a900463ffffffff16925092"
      + "50509091565b60005481565b60006001600260008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffff"
      + "ffffffffffffffffff16815260200190815260200160002060009054906101000a900463ffffffff160390509190505600a165627a7a72"
      + "3058201902c6738a01c68a53bd4a3c9e24b37a14dece9892c1e36c193136d9589698ef0029";
    final Quantity from = Quantity.of(orig);
    final String hex = from.toHex();
    Assert.assertEquals(orig, hex);
  }

  @Test
  public void testToBytes() {
    Assert.assertEquals(Quantity.of(Quantity.of(0).toBytes()), Quantity.of(0));
  }
}