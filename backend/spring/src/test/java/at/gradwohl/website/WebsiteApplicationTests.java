package at.gradwohl.website;

import at.gradwohl.website.model.dienstplan.Dienstplan;
import at.gradwohl.website.model.dienstplan.DienstplanId;
import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.firma.Firma;
import at.gradwohl.website.model.kundenbestellung.Kundenbestellung;
import at.gradwohl.website.model.kundenbestellung.KundenbestellungId;
import at.gradwohl.website.model.lieferbar.Lieferbar;
import at.gradwohl.website.model.lieferbar.LieferbarId;
import at.gradwohl.website.model.lieferbar.Wochentag;
import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import at.gradwohl.website.model.mitarbeiterrole.MitarbeiterRole;
import at.gradwohl.website.model.nachricht.Nachricht;
import at.gradwohl.website.model.nachrichtSenden.NachrichtSenden;
import at.gradwohl.website.model.nachrichtSenden.NachrichtSendenId;
import at.gradwohl.website.model.produkt.Produkt;
import at.gradwohl.website.model.produktgruppe.Produktgruppe;
import at.gradwohl.website.model.vorlage.Vorlage;
import at.gradwohl.website.model.vorlage.VorlageId;
import at.gradwohl.website.model.warenbestellung.Warenbestellung;
import at.gradwohl.website.model.warenbestellung.WarenbestellungId;
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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@SpringBootTest
class WebsiteApplicationTests {

	@Autowired
	RoleRepository roleRepo;
	@Autowired
	DienstplanRepository dienstplanRepository;
	@Autowired
	FilialeRepository filialeRepository;
	@Autowired
	MitarbeiterRepository mitarbeiterRepository;
	@Autowired
	FirmaRepository firmaRepository;
	@Autowired
	ProduktgruppeRepository produktgruppeRepository;
	@Autowired
	ProduktRepository produktRepository;
	@Autowired
	VorlageRepository vorlageRepository;
	@Autowired
	KundenbestellungRepository kundenbestellungRepository;
	@Autowired
	WarenbestellungRepository warenbestellungRepository;
	@Autowired
	LieferbarRepository lieferbarRepository;
	@Autowired
	NachrichtRepository nachrichtRepository;
	@Autowired
	NachrichtSendenRepository nachrichtSendenRepository;

	@Test
	void saveRole(){
		MitarbeiterRole role =
				MitarbeiterRole.builder()
						.role("Mitarbeiter")
						.build();

		roleRepo.save(role);
	}

	@Test
	void saveDienstplan(){
		MitarbeiterRole role =
				MitarbeiterRole.builder()
						.role("Mitarbeiter")
						.build();

		roleRepo.save(role);

		Firma firma =
				Firma.builder()
						.name("Wien")
						.build();

		firmaRepository.save(firma);

		Filiale filiale2 =
				Filiale.builder()
						.id(2)
						.name("Mariahilfer")
						.firma(firma)
						.build();

		filialeRepository.save(filiale2);

		Mitarbeiter mitarbeiter =
				Mitarbeiter.builder()
						.id(1)
						.name("Antonio")
						.role(role)
						.filiale(filiale2)
						.build();

		mitarbeiterRepository.save(mitarbeiter);

		Filiale filiale =
				Filiale.builder()
						.id(1)
						.name("Hietzing")
						.filialleiter(mitarbeiter)
						.build();
		filialeRepository.save(filiale);

		DienstplanId D_ID =
				DienstplanId.builder()
						.datum(LocalDate.of(2022, 5, 12))
						.filiale(filiale)
						.von(LocalTime.of(5, 30))
						.mitarbeiter(mitarbeiter)
						.build();

		Dienstplan dienstplan =
				Dienstplan.builder()
						.id(D_ID)
						.bis(LocalTime.of(12, 00))
						.build();

		dienstplanRepository.save(dienstplan);

	}

	@Test
	void saveVorlage(){
		Produktgruppe produktgruppe=
				Produktgruppe.builder()
						.name("Topfen")
						.build();

		produktgruppeRepository.save(produktgruppe);

		Produkt produkt =
				Produkt.builder()
						.id(1)
						.name("Topfenstrudel")
						.bio(true)
						.mehl("Dinkel")
						.hb(true)
						.produktgruppe(produktgruppe)
						.build();

		produktRepository.save(produkt);

		Filiale filiale =
				Filiale.builder()
						.id(1)
						.name("Hietzing")
						.filialleiter(null)
						.firma(null)
						.build();

		filialeRepository.save(filiale);

		VorlageId vorlageId =
				VorlageId.builder()
						.id(1)
						.produkt(produkt)
						.filiale(filiale)
						.build();

		Vorlage vorlage	=
				Vorlage.builder()
						.id(vorlageId)
						.menge(1.32)
						.build();

		vorlageRepository.save(vorlage);
	}

	@Test
	void saveKundenbestellung(){
		Produktgruppe produktgruppe=
				Produktgruppe.builder()
						.name("Topfen")
						.build();

		produktgruppeRepository.save(produktgruppe);

		Produkt produkt =
				Produkt.builder()
						.id(1)
						.name("Topfenstrudel")
						.bio(true)
						.mehl("Dinkel")
						.hb(true)
						.produktgruppe(produktgruppe)
						.build();

		produktRepository.save(produkt);

		Filiale filiale =
				Filiale.builder()
						.id(1)
						.name("Hietzing")
						.filialleiter(null)
						.firma(null)
						.build();

		filialeRepository.save(filiale);

		KundenbestellungId kundenId =
				KundenbestellungId.builder()
						.kunde("Maria")
						.datum(LocalDate.of(2022, 5, 12))
						.produkt(produkt)
						.filiale(filiale)
						.build();

		Kundenbestellung kundenbestellung =
				Kundenbestellung.builder()
						.id(kundenId)
						.menge(1.2)
						.build();

		kundenbestellungRepository.save(kundenbestellung);
	}

	@Test
	void saveWarenbestellung(){
		Produktgruppe produktgruppe=
				Produktgruppe.builder()
						.name("Topfen")
						.build();

		produktgruppeRepository.save(produktgruppe);

		Produkt produkt =
				Produkt.builder()
						.id(1)
						.name("Topfenstrudel")
						.bio(true)
						.mehl("Dinkel")
						.hb(true)
						.produktgruppe(produktgruppe)
						.build();

		produktRepository.save(produkt);

		Filiale filiale =
				Filiale.builder()
						.id(1)
						.name("Hietzing")
						.filialleiter(null)
						.firma(null)
						.build();

		filialeRepository.save(filiale);

		WarenbestellungId wareId =
				WarenbestellungId.builder()
						.produkt(produkt)
						.filiale(filiale)
						.datum(LocalDate.of(2022, 5, 12))
						.build();

		Warenbestellung warenbestellung =
				Warenbestellung.builder()
						.id(wareId)
						.menge(1.2)
						.build();


		warenbestellungRepository.save(warenbestellung);
	}

	@Test
	void saveLieferbar(){
		Produktgruppe produktgruppe=
				Produktgruppe.builder()
						.name("Topfen")
						.build();

		produktgruppeRepository.save(produktgruppe);

		Produkt produkt =
				Produkt.builder()
						.id(1)
						.name("Topfenstrudel")
						.bio(true)
						.mehl("Dinkel")
						.hb(true)
						.produktgruppe(produktgruppe)
						.build();

		produktRepository.save(produkt);

		Firma firma =
				Firma.builder()
						.name("Wien")
						.build();

		firmaRepository.save(firma);

		LieferbarId lieferbarId =
				LieferbarId.builder()
						.firma(firma)
						.produkt(produkt)
						.build();

		Lieferbar lieferbar =
				Lieferbar.builder()
						.id(lieferbarId)
						.von(Wochentag.Montag)
						.bis(Wochentag.Dienstag)
						.build();

		lieferbarRepository.save(lieferbar);

	}

	@Test
	void saveNachrichtSenden(){
		Firma firma =
				Firma.builder()
						.name("Wien")
						.build();

		firmaRepository.save(firma);

		Filiale filiale2 =
				Filiale.builder()
						.id(2)
						.name("Mariahilfer")
						.firma(firma)
						.build();

		filialeRepository.save(filiale2);

		Nachricht nachricht = Nachricht.builder()
				.id(1)
				.build();

		nachricht.setParagraphs(List.of(
				"This is the first paragraph.",
				"This is the second paragraph.",
				"And this is the third paragraph."
		));

		nachrichtRepository.save(nachricht);

		NachrichtSendenId nachrichtSendenId =
				NachrichtSendenId.builder()
						.filiale(filiale2)
						.nachricht(nachricht)
						.build();

		NachrichtSenden nachrichtSenden =
				NachrichtSenden.builder()
								.id(nachrichtSendenId)
						        .build();

		nachrichtSendenRepository.save(nachrichtSenden);
	}
}
