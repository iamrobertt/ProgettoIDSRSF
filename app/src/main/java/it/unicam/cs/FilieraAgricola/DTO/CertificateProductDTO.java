package it.unicam.cs.FilieraAgricola.DTO;

import lombok.Data;

@Data
public class CertificateProductDTO {

    private long certificateID;
    private String CertificateType;
    private String certificateFilePath;
    private long productID;

}
