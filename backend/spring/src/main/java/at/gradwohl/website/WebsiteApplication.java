package at.gradwohl.website;

import at.gradwohl.website.repository.dienstplan.DienstplanRepository;
import at.gradwohl.website.repository.filiale.FilialeRepository;
import at.gradwohl.website.repository.firma.FirmaRepository;
import at.gradwohl.website.repository.kundenbestellung.KundenbestellungRepository;
import at.gradwohl.website.repository.lieferbar.LieferbarRepository;
import at.gradwohl.website.repository.mitarbeiter.MitarbeiterRepository;
import at.gradwohl.website.repository.nachricht.NachrichtRepository;
import at.gradwohl.website.repository.nachrichtsenden.NachrichtSendenRepository;
import at.gradwohl.website.repository.produkt.ProduktRepository;
import at.gradwohl.website.repository.produktgruppe.ProduktgruppeRepository;
import at.gradwohl.website.repository.role.RoleRepository;
import at.gradwohl.website.repository.vorlage.VorlageRepository;
import at.gradwohl.website.repository.warenbestellung.WarenbestellungRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping(path = "api/v1")
public class WebsiteApplication {

	public static void main(String[] args) { SpringApplication.run(WebsiteApplication.class, args); }


	@Bean
	CommandLineRunner commandLineRunner(DienstplanRepository dienstplanRepo,
										RoleRepository roleRepo,
										FilialeRepository filialeRepository,
										MitarbeiterRepository mitarbeiterRepository,
										FirmaRepository firmaRepository,
										ProduktRepository produktRepository,
										ProduktgruppeRepository produktgruppeRepository,
										VorlageRepository vorlageRepository,
										KundenbestellungRepository kundenbestellungRepository,
										WarenbestellungRepository warenbestellungRepository,
										LieferbarRepository lieferbarRepository,
										NachrichtRepository nachrichtRepository,
										NachrichtSendenRepository nachrichtSendenRepository) {
		return args -> {


		};
	}

	@GetMapping
	public String getTest(){
		return "Test";
	}

}
