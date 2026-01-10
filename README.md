# j-crypt-credentials

**j-crypt-credentials** is a Java library that standardizes cryptographic algorithm outputs into  **MCF (Modular Crypt Format)**.  
It provides a complete set of tools for **serialization, parsing and conversion** of MCF structures.

The library is designed to be used as a reusable dependency.

`Note` - Encryptors are meant to encrypt credentials, that are needed to be reused for service functionality, like email addresses, phone numbers, etc.

---

## Features

- Standardized output format based on **Modular Crypt Format (MCF)**
- API for cryptographic algorithm implementations
- Reflection based MCF serialization and parsing
- Configurable type conversion
- Annotation driven object mapping
- Default implementations

---

## Use Cases

- Standardizing encryption outputs
- Interoperable cryptographic storage formats
- Libraries or services requiring MCF compatibility

## Modules & Packages

### API module
The `api` package contains **interfaces intended for third-party applications**.

Modules with the `impl` postfix provide **reference implementations** of the API that return results in MCF format.

---

## Common module

The `common` module contains core functionality shared across the library.

- Converter
- Parser
- Annotations
- Serializer

---

## Modules with 'internal' prefix
Modules with 'internal' prefix are used inside project and are not deployed

---

## Modules with 'impl' postfix
Modules with 'impl' postfix are implementations of `API` module, and provide Modular Crypt Format algorithm outputs

---

## Details
For more details see package descriptions