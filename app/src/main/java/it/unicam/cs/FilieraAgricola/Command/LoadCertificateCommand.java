package it.unicam.cs.FilieraAgricola.Command;

import it.unicam.cs.FilieraAgricola.Certificate.Certificate;
import it.unicam.cs.FilieraAgricola.Certificate.CertificateLoader;
import it.unicam.cs.FilieraAgricola.Certificate.CertificateLoaderFactory;
import it.unicam.cs.FilieraAgricola.User.User;
import it.unicam.cs.FilieraAgricola.User.UserRole;

import java.util.ArrayList;
import java.util.List;

public class LoadCertificateCommand extends Command<Certificate>{


    public LoadCertificateCommand(User user, Certificate item) {
        super(user, item);
    }

    @Override
    public List<UserRole> getNeededAuthorization() {
        List<UserRole> neededRoles = new ArrayList<>();
        neededRoles.add(UserRole.SELLER);
        return neededRoles;
    }

    @Override
    public boolean hasCallerNeededAuthorization() {
        return getNeededAuthorization().contains(this.user.getUserRole());
    }

    @Override
    public void execute() {
        CertificateLoaderFactory certificateLoaderFactory = new CertificateLoaderFactory();
        CertificateLoader certificateLoader = certificateLoaderFactory.getCertificateLoader(this.item.getClass());
        certificateLoader.loadCertificate(this.item);
    }
}
