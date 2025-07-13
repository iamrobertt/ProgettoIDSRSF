package it.unicam.cs.FilieraAgricola.Certificate;

import it.unicam.cs.FilieraAgricola.CheckStrategy.LoadCertificateCheckStrategy;
import it.unicam.cs.FilieraAgricola.Command.Command;
import it.unicam.cs.FilieraAgricola.Command.CommandInvoker;
import it.unicam.cs.FilieraAgricola.Command.LoadCertificateCommand;
import it.unicam.cs.FilieraAgricola.Exception.InsufficientUserAuthorizationException;
import it.unicam.cs.FilieraAgricola.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class CertificateManager {

    @Autowired
    private LoadCertificateCheckStrategy loadCertificateCheckStrategy;

    @Value("${app.upload.dir}")
    private String uploadDir;

    public void loadCertificateRequest(User user, CertificateProduct certificate, MultipartFile certificateFile) {

        if(!this.loadCertificateCheckStrategy.validate(user, certificate, certificateFile))
            throw new IllegalArgumentException("Certificate non valid for loading.");

        Command<Certificate> loadCertificateCommand = new LoadCertificateCommand(user, certificate);

        if(!loadCertificateCommand.hasCallerNeededAuthorization())
            throw new InsufficientUserAuthorizationException("Insufficient authorization to perform a loading product request");

        CommandInvoker invoker = new CommandInvoker();

        invoker.setCommand(loadCertificateCommand);
        invoker.invoke();

        //Saving locally the file
        try {
            File convFile = new File(uploadDir + user.getUserID() + "/" + certificate.getCertificateFilePath());
            convFile.getParentFile().mkdirs();
            try (FileOutputStream fos = new FileOutputStream(convFile)) {
                fos.write(certificateFile.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
