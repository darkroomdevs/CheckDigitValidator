[![](https://jitpack.io/v/darkroomdevs/CheckDigitValidator.svg)](https://jitpack.io/#darkroomdevs/CheckDigitValidator)

### Description

This lib compute the check digit of a sequence through a slightly more complex method thet take the
weighted sum of the digits, modulo 10, with different weights for each number position.

A check digit is a form of redundancy check used for error detection on identification numbers,
such as bank account numbers, which are used in an application where they will at least sometimes
be input manually. It is analogous to a binary parity bit used to check for errors in computer-generated
data. It consists of one or more digits computed by an algorithm from the other digits (or letters)
in the sequence input.

With a check digit, one can detect simple errors in the input of a series of characters (usually digits)
such as a single mistyped digit or some permutations of two successive digits.

### Reference

https://en.wikipedia.org/wiki/Check_digit

### Current Validators Examples

* Brazilian Documents
  * CPF
  * CNPJ
* Brazilian Banks
  * Banco do Brasil

### Use

**Step 1.** Add the JitPack repository to your build file:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

**Step 2.** Add the dependency:
```xml
<dependency>
    <groupId>com.github.darkroomdevs</groupId>
    <artifactId>CheckDigitValidator</artifactId>
    <version>0.1.0</version>
</dependency>
```
