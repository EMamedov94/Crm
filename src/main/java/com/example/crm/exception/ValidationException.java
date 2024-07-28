<<<<<<< HEAD
package com.example.crm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
public class ValidationException extends RuntimeException {
    private List<String> errors;
}
=======
package com.example.crm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
public class ValidationException extends RuntimeException {
    private List<String> errors;
}
>>>>>>> 286b440547e40e1d18d973cda5a528304e0c05ea
