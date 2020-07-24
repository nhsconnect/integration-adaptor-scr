package uk.nhs.adaptors.scr.utils.spineMockSetup.interfaces;

public interface SpineMockSetupWithHttpStatusCode {
    SpineMockSetupForResponseContent withHttpStatusCode(Integer httpStatusCode);
}
