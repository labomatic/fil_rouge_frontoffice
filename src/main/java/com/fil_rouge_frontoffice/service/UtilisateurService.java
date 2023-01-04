package com.fil_rouge_frontoffice.service;

import com.fil_rouge_frontoffice.controller.dto.SignupRequest;
import com.fil_rouge_frontoffice.controller.dto.UtilisateurDto;
import com.fil_rouge_frontoffice.entity.AvoirDroitsCrudPlanningAutreUtilisateur;
import com.fil_rouge_frontoffice.entity.Role;
import com.fil_rouge_frontoffice.entity.StatutCompte;
import com.fil_rouge_frontoffice.entity.Utilisateur;
import com.fil_rouge_frontoffice.exception.CompteDejaExistantException;
import com.fil_rouge_frontoffice.properties.FileStorageProperties;
import com.fil_rouge_frontoffice.repository.RoleRepository;
import com.fil_rouge_frontoffice.repository.StatutCompteRepository;
import com.fil_rouge_frontoffice.repository.UtilisateurRepository;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UtilisateurService {
    @Autowired
    ServletContext context;
    @Autowired
    private FileStorageProperties fileStorageProperties;

    @Autowired
    private UtilisateurRepository utilisateurRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private StatutCompteRepository statutCompteRepo;

    @Autowired
    private PasswordEncoder encoder;
    private Path fileStorageLocation;
    private final Path root = Paths.get("photos");


    public UtilisateurDto findById(Long id) {
        Optional<Utilisateur> utilisateur = utilisateurRepo.findById(id);
        UtilisateurDto utilisateurDto = UtilisateurDto.from(utilisateur.orElse(null));
        return utilisateurDto;
    }

    public UtilisateurDto findByMail(String mail) {
        Optional<Utilisateur> utilisateur = utilisateurRepo.findUtilisateurByMail(mail);
        UtilisateurDto utilisateurDto = UtilisateurDto.from(utilisateur.orElse(null));
        return utilisateurDto;
    }

    public void signup(SignupRequest dto) throws CompteDejaExistantException {

        boolean utilisateurExiste = utilisateurRepo.existsByMail(dto.getMail());

        if(utilisateurExiste) {
            throw new CompteDejaExistantException();
        } else {
            storeFile(dto.getPhoto(), dto.getMail());
            dto.setPassword(encoder.encode(dto.getPassword()));
            Utilisateur utilisateur = dto.toUtilisateur();
            setRoleUtilisateur(utilisateur);
            setStatutCompteActif(utilisateur);
            utilisateurRepo.save(utilisateur);
        }
    }

    public boolean updateProfil(SignupRequest dto){
        boolean utilisateurExiste = utilisateurRepo.existsById(dto.getId());
        if(utilisateurExiste){
            storeFile(dto.getPhoto(), dto.getMail());
            Utilisateur utilisateur = utilisateurRepo.findById(dto.getId()).get();
            utilisateur.setPrenom(dto.getPrenom());
            utilisateur.setVille(dto.getVille());
            utilisateur.setPays(dto.getPays());
            utilisateur.setNom(dto.getNom());
            utilisateur.setPhoto(dto.genererNomPhoto());
            utilisateurRepo.save(utilisateur);
            return true;
        }
        return false;
    }
    private void setRoleUtilisateur(Utilisateur utilisateur){
        utilisateur.setRole(setRole());
    }

    public Role setRole() {
        return roleRepo.findRoleByIntitule("utilisateur");
    }
    private void setStatutCompteActif(Utilisateur utilisateur){
        utilisateur.setStatutCompte(setStatutCompte());
    }
    public StatutCompte setStatutCompte() {
        return statutCompteRepo.findStatutCompteByIntitule("actif");
    }

    private void storeFile(MultipartFile file, String fileName) {

        String photoName = file.getOriginalFilename();
        int index = photoName.lastIndexOf(".");
        String photoExtension = photoName.substring(index);
        try {
            this.fileStorageLocation = Path.of(new FileSystemResource("").getFile().getAbsolutePath() + "/photos");
            try {
                Files.createDirectories(this.fileStorageLocation);
            } catch (Exception ex) {}
            Path targetLocation = this.fileStorageLocation.resolve(fileName + photoExtension);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {}
    }
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
    public Utilisateur getConnectedUtilisateur(){
        return (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public List<UtilisateurDto> searchUtilisateurs(String string) {
        List<Utilisateur> utilisateurs = utilisateurRepo.searchUtilisateursByKeyword(string);
        return utilisateurs.stream()
                .map(utilisateur -> UtilisateurDto.from(utilisateur))
                .collect(Collectors.toList());
    }

    public boolean isLectureAutorisee(String mailProprietaire, String mailAutre){
        List<AvoirDroitsCrudPlanningAutreUtilisateur> droits = utilisateurRepo.peutLire(mailProprietaire, mailAutre);
        return droits != null && droits.size() > 0;
    }
    public boolean isEcritureAutorisee(String mailProprietaire, String mailAutre){
        List<AvoirDroitsCrudPlanningAutreUtilisateur> droits = utilisateurRepo.peutCreer(mailProprietaire, mailAutre);
        return droits != null && droits.size() > 0;
    }
    public boolean isModificationAutorisee(String mailProprietaire, String mailAutre){
        List<AvoirDroitsCrudPlanningAutreUtilisateur> droits = utilisateurRepo.peutModifier(mailProprietaire, mailAutre);
        return droits != null && droits.size() > 0;
    }
    public boolean isSuppressionAutorisee(String mailProprietaire, String mailAutre){
        List<AvoirDroitsCrudPlanningAutreUtilisateur> droits = utilisateurRepo.peutSupprimer(mailProprietaire, mailAutre);
        return droits != null && droits.size() > 0;
    }
}
