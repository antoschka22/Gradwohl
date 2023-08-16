package at.gradwohl.website.service;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.firma.Firma;
import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import at.gradwohl.website.model.mitarbeiterrole.MitarbeiterRole;
import at.gradwohl.website.model.produkt.Mehl;
import at.gradwohl.website.model.produkt.Produkt;
import at.gradwohl.website.model.produktgruppe.Produktgruppe;
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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class initApplication {

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
            Firma wien =
                    Firma.builder()
                            .name("Wien")
                            .build();
            Firma burgenland=
                    Firma.builder()
                            .name("Burgenland")
                            .build();

            Firma teigwerkstatt =
                    Firma.builder()
                            .name("Olivers Teigwerkstätte")
                            .build();

            List<Firma> firmen = Arrays.asList(
                wien, burgenland, teigwerkstatt
            );

            firmaRepository.saveAll(firmen);


            Filiale fleischmarkt =
                    Filiale.builder()
                            .name("Fleischmarkt")
                            .firma(wien)
                            .filialleiter(null)
                            .build();

            Filiale naglergasse =
                    Filiale.builder()
                            .name("Naglergasse")
                            .firma(wien)
                            .filialleiter(null)
                            .build();
            Filiale landstrasse =
                    Filiale.builder()
                            .name("Landstraße")
                            .firma(wien)
                            .filialleiter(null)
                            .build();

            Filiale mariahilfer =
                    Filiale.builder()
                            .name("Mariahilferstraße")
                            .firma(wien)
                            .filialleiter(null)
                            .build();

            Filiale ziegler =
                    Filiale.builder()
                            .name("Zieglergasse")
                            .firma(wien)
                            .filialleiter(null)
                            .build();

            Filiale josefstadt =
                    Filiale.builder()
                            .name("Josefstadt")
                            .firma(wien)
                            .filialleiter(null)
                            .build();

            Filiale hietzing =
                    Filiale.builder()
                            .name("Hietzing")
                            .firma(wien)
                            .filialleiter(null)
                            .build();

            Filiale doebling =
                    Filiale.builder()
                            .name("Döbling")
                            .firma(wien)
                            .filialleiter(null)
                            .build();

            Filiale schwechat =
                    Filiale.builder()
                            .name("Schwechat")
                            .firma(wien)
                            .filialleiter(null)
                            .build();

            Filiale moedling =
                    Filiale.builder()
                            .name("Mödling")
                            .firma(wien)
                            .filialleiter(null)
                            .build();

            Filiale baden =
                    Filiale.builder()
                            .name("Baden")
                            .firma(wien)
                            .filialleiter(null)
                            .build();

            Filiale neustadt =
                    Filiale.builder()
                            .name("Wiener Neustadt")
                            .firma(wien)
                            .filialleiter(null)
                            .build();

            Filiale eisenstadt =
                    Filiale.builder()
                            .name("Eisenstadt")
                            .firma(burgenland)
                            .filialleiter(null)
                            .build();

            Filiale deutschkreutz =
                    Filiale.builder()
                            .name("Deutschkreutz")
                            .firma(burgenland)
                            .filialleiter(null)
                            .build();

            Filiale weppersdorf =
                    Filiale.builder()
                            .name("Weppersdorf")
                            .firma(burgenland)
                            .filialleiter(null)
                            .build();

            Filiale oberpullendorf =
                    Filiale.builder()
                            .name("Oberpullendorf")
                            .firma(burgenland)
                            .filialleiter(null)
                            .build();

            Filiale alserStrasse =
                    Filiale.builder()
                            .name("Alser Straße")
                            .firma(teigwerkstatt)
                            .filialleiter(null)
                            .build();

            Filiale goldschmied =
                    Filiale.builder()
                            .name("Goldschmiedgasse")
                            .firma(teigwerkstatt)
                            .filialleiter(null)
                            .build();

            Filiale krugerstrasse =
                    Filiale.builder()
                            .name("Krugerstrasse")
                            .firma(teigwerkstatt)
                            .filialleiter(null)
                            .build();

            Filiale plankengasse =
                    Filiale.builder()
                            .name("Plankengasse")
                            .firma(teigwerkstatt)
                            .filialleiter(null)
                            .build();

            List<Filiale> filialen = Arrays.asList(
                plankengasse, krugerstrasse, goldschmied, alserStrasse, oberpullendorf, weppersdorf, deutschkreutz, eisenstadt, neustadt, baden, moedling, schwechat, doebling, hietzing, josefstadt, mariahilfer, ziegler, landstrasse, naglergasse, fleischmarkt
            );

            filialeRepository.saveAll(filialen);

            MitarbeiterRole zentrale =
                    MitarbeiterRole.builder()
                            .role("Zentrale")
                            .build();

            MitarbeiterRole verkauf =
                    MitarbeiterRole.builder()
                            .role("Verkauf")
                            .build();

            MitarbeiterRole leiter =
                    MitarbeiterRole.builder()
                            .role("Leiter")
                            .build();


            List<MitarbeiterRole> rolen = Arrays.asList(
                    zentrale, verkauf, leiter
            );

            roleRepo.saveAll(rolen);

            Mitarbeiter barbara =
                    Mitarbeiter.builder()
                            .role(leiter)
                            .name("Barbara")
                            .filiale(hietzing)
                            .build();

            Mitarbeiter szimone =
                    Mitarbeiter.builder()
                            .role(verkauf)
                            .name("Szimone")
                            .filiale(hietzing)
                            .build();

            Mitarbeiter christa =
                    Mitarbeiter.builder()
                            .role(leiter)
                            .name("Christa")
                            .filiale(ziegler)
                            .build();

            Mitarbeiter nicole =
                    Mitarbeiter.builder()
                            .role(zentrale)
                            .name("Nicole")
                            .filiale(null)
                            .build();
            List<Mitarbeiter> mitarbeiter = Arrays.asList(
              barbara, szimone, nicole, christa
            );

            //filialeRepository.findBy()

            mitarbeiterRepository.saveAll(mitarbeiter);


            Produktgruppe brote=
                    Produktgruppe.builder()
                            .name("VK Brote")
                            .build();

            Produktgruppe gebaeck=
                    Produktgruppe.builder()
                            .name("VK Kleingebäck")
                            .build();

            Produktgruppe jourGebaeck=
                    Produktgruppe.builder()
                            .name("VK Jour Gebäck")
                            .build();

            Produktgruppe mehlspeisen=
                    Produktgruppe.builder()
                            .name("VK Mehlspeisen")
                            .build();

            Produktgruppe kekse=
                    Produktgruppe.builder()
                            .name("VK Kekse")
                            .build();

            Produktgruppe kuchen=
                    Produktgruppe.builder()
                            .name("VK Kuchen")
                            .build();

            Produktgruppe torten=
                    Produktgruppe.builder()
                            .name("VK Torten")
                            .build();

            Produktgruppe sontiges=
                    Produktgruppe.builder()
                            .name("VK Sonstiges")
                            .build();

            Produktgruppe organisatorisch=
                    Produktgruppe.builder()
                            .name("Organisatorisch")
                            .build();

            Produktgruppe stange =
                    Produktgruppe.builder()
                            .name("VK Brot Stangen")
                            .build();

            Produktgruppe snack =
                    Produktgruppe.builder()
                            .name("Snack")
                            .build();


            List<Produktgruppe> produktgruppen = Arrays.asList(
                    snack, brote, gebaeck, jourGebaeck, mehlspeisen, kuchen, torten, sontiges, organisatorisch, kekse, stange
            );

            produktgruppeRepository.saveAll(produktgruppen);


            Produkt roggenbrot =
                    Produkt.builder()
                            .id(60)
                            .name("Roggenbrot")
                            .bio(true)
                            .mehl(Mehl.Roggen)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();


            Produkt bauernbrot =
                    Produkt.builder()
                            .id(66)
                            .name("Bauernbrot")
                            .bio(true)
                            .mehl(Mehl.Pharao)
                            .mehlMischung(Mehl.Roggen)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt bauernbrotHB =
                    Produkt.builder()
                            .id(2066)
                            .name("Bauernbrot HB")
                            .bio(true)
                            .mehl(Mehl.Roggen)
                            .mehlMischung(Mehl.Roggen)
                            .hb(true)
                            .produktgruppe(brote)
                            .build();


            Produkt dinkelbrotOhneHefe =
                    Produkt.builder()
                            .id(906)
                            .name("Dinkelbrot ohne Hefe")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt dinkelbrotOhneHefeHB =
                    Produkt.builder()
                            .id(2906)
                            .name("Dinkelbrot ohne Hefe HB")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .hb(true)
                            .produktgruppe(brote)
                            .build();


            Produkt pharaobrotOhneHefe =
                    Produkt.builder()
                            .id(68)
                            .name("Pharaobrot ohne Hefe")
                            .bio(true)
                            .mehl(Mehl.Pharao)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt dinkelbrot =
                    Produkt.builder()
                            .id(71)
                            .name("Dinkelbrot")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt dinkelbrotHB =
                    Produkt.builder()
                            .id(2071)
                            .name("Dinkelbrot HB")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .hb(true)
                            .produktgruppe(brote)
                            .build();

            Produkt dinkeltoast =
                    Produkt.builder()
                            .id(72)
                            .name("Dinkeltoast")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt leinsamenAmaranthbrotOhneHefe =
                    Produkt.builder()
                            .id(196)
                            .name("Leinsamen-Amaranthbrot ohne Hefe")
                            .bio(true)
                            .mehl(Mehl.Pharao)
                            .mehlMischung(Mehl.Roggen)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt leinsamenAmaranthbrotOhneHefeHB =
                    Produkt.builder()
                            .id(2196)
                            .name("Leinsamen-Amarantbrot ohne Hefe HB")
                            .bio(true)
                            .mehl(Mehl.Roggen)
                            .mehlMischung(Mehl.Roggen)
                            .hb(true)
                            .produktgruppe(brote)
                            .build();

            Produkt backfermentbrotOhneHefe =
                    Produkt.builder()
                            .id(227)
                            .name("Backfermentbrot ohne Hefe")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt karottenNussbrot =
                    Produkt.builder()
                            .id(288)
                            .name("Karotten-Nussbrot")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt karottenNussHB =
                    Produkt.builder()
                            .id(2288)
                            .name("Karotte-Nussbrot HB")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .hb(true)
                            .produktgruppe(brote)
                            .build();

            Produkt kraftbrotOhneHefe =
                    Produkt.builder()
                            .id(492)
                            .name("BT Kraftbrot ohne Hefe")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt kraftbrotOhneHefeHB =
                    Produkt.builder()
                            .id(2492)
                            .name("Kraftbrot ohne Hefe HB")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .hb(true)
                            .produktgruppe(brote)
                            .build();

            Produkt chiabrotOhneHefe =
                    Produkt.builder()
                            .id(117)
                            .name("Chiabrot ohne Hefe")
                            .bio(true)
                            .mehl(Mehl.Pharao)
                            .mehlMischung(Mehl.Roggen)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt chiabrotOhneHefeHB =
                    Produkt.builder()
                            .id(2117)
                            .name("Chiabrot ohne Hefe HB")
                            .bio(true)
                            .mehl(Mehl.Pharao)
                            .mehlMischung(Mehl.Roggen)
                            .hb(true)
                            .produktgruppe(brote)
                            .build();

            Produkt dinkelbrotStange =
                    Produkt.builder()
                            .id(449)
                            .name("Dinkelbrot Stange")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .hb(false)
                            .produktgruppe(stange)
                            .build();

            Produkt siebenkornStange =
                    Produkt.builder()
                            .id(206)
                            .name("Siebenkorn Stange")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(Mehl.Roggen)
                            .hb(false)
                            .produktgruppe(stange)
                            .build();

            Produkt bauernbrotStange =
                    Produkt.builder()
                            .id(450)
                            .name("Bauernbrot Stange")
                            .bio(true)
                            .mehl(Mehl.Pharao)
                            .mehlMischung(Mehl.Roggen)
                            .hb(false)
                            .produktgruppe(stange)
                            .build();

            Produkt kuerbiskernbrot =
                    Produkt.builder()
                            .id(821)
                            .name("Kürbiskernbrot")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt kuerbisbrotHB =
                    Produkt.builder()
                            .id(2821)
                            .name("Kürbisbrot HB")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .hb(true)
                            .produktgruppe(brote)
                            .build();

            Produkt suesskartoffelbrot =
                    Produkt.builder()
                            .id(831)
                            .name("Süßkartoffelbrot")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt suesskartoffelbrotHB =
                    Produkt.builder()
                            .id(2831)
                            .name("Sueßkartoffelbrot HB")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .hb(true)
                            .produktgruppe(brote)
                            .build();

            Produkt olivenbrot =
                    Produkt.builder()
                            .id(498)
                            .name("Olivenbrot")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt olivenbrotHB =
                    Produkt.builder()
                            .id(2498)
                            .name("Olivenbrot HB")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .hb(true)
                            .produktgruppe(brote)
                            .build();

            Produkt roteRuebenbrot =
                    Produkt.builder()
                            .id(408)
                            .name("Rote Rübenbrot")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt roteRuebenbrotHB =
                    Produkt.builder()
                            .id(2408)
                            .name("Rote Rübenbrot HB")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .hb(true)
                            .produktgruppe(brote)
                            .build();

            Produkt zwiebelbrot =
                    Produkt.builder()
                            .id(409)
                            .name("Zwiebelbrot")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt zwiebelbrotHB =
                    Produkt.builder()
                            .id(2409)
                            .name("Zwiebelbrot HB")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .hb(true)
                            .produktgruppe(brote)
                            .build();


            Produkt nussbrotstange =
                    Produkt.builder()
                            .id(344)
                            .name("Nussbrotstange")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();


            Produkt nussbrotleibRund =
                    Produkt.builder()
                            .id(74)
                            .name("Nussbrotleib Rund")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt nussbrotlaibRundHB =
                    Produkt.builder()
                            .id(2074)
                            .name("Nussbrotlaib Rund HB")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .hb(true)
                            .produktgruppe(brote)
                            .build();

            Produkt baerlauchbrot =
                    Produkt.builder()
                            .id(754)
                            .name("Bärlauchbrot")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt baerlauchbrotHB =
                    Produkt.builder()
                            .id(2754)
                            .name("Bärlauchbrot HB")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .hb(true)
                            .produktgruppe(brote)
                            .build();

            Produkt sandwich =
                    Produkt.builder()
                            .id(268)
                            .name("Sandwich")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt baguette =
                    Produkt.builder()
                            .id(247)
                            .name("Baguette")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt baguetteHB =
                    Produkt.builder()
                            .id(2247)
                            .name("Baguette HB")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .hb(true)
                            .produktgruppe(brote)
                            .build();


            Produkt kaesestangerl =
                    Produkt.builder()
                            .id(129)
                            .name("Käsestangerl")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt kaesestangerlHB =
                    Produkt.builder()
                            .id(2129)
                            .name("Käsestangerl (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt vitalwecker =
                    Produkt.builder()
                            .id(458)
                            .name("Vitalweckerl")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt vitalweckerHB =
                    Produkt.builder()
                            .id(2458)
                            .name("Vitalwecker (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt olinebweckerl =
                    Produkt.builder()
                            .id(2498)
                            .name("Olivenweckerl")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt olivenweckerlHB =
                    Produkt.builder()
                            .id(2498)
                            .name("Olivenweckerl (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt dinkelsemmel =
                    Produkt.builder()
                            .id(119)
                            .name("Dinkelsemmel")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt dinkelsemmelHB =
                    Produkt.builder()
                            .id(2119)
                            .name("Dinkelsemmel (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt dinkelkornsitz =
                    Produkt.builder()
                            .id(113)
                            .name("Dinkelkornspitz")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt dinkelkornsitzHB =
                    Produkt.builder()
                            .id(2113)
                            .name("Dinkelkornspitz (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt dinkelkornspitzSalz =
                    Produkt.builder()
                            .id(285)
                            .name("Dinkelkornspitz Salz")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt dinkelweckerl =
                    Produkt.builder()
                            .id(124)
                            .name("Dinkelweckerl")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt dinkelweckerlHB =
                    Produkt.builder()
                            .id(2124)
                            .name("Dinkelweckerl (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();


            Produkt dinkelkornspitzOhneBestreu =
                    Produkt.builder()
                            .id(444)
                            .name("Dinkelkornspitz ohne Betreung")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt dinkelkornspitzOhneBestreuHB =
                    Produkt.builder()
                            .id(2444)
                            .name("Dinkelkornspitz ohne Betreu (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt pharaoweckerl =
                    Produkt.builder()
                            .id(417)
                            .name("Pharaoweckerl")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt pharaoweckerlHB =
                    Produkt.builder()
                            .id(2417)
                            .name("Pharaoweckerl (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();


            Produkt chiaweckerl =
                    Produkt.builder()
                            .id(140)
                            .name("Chiaweckerl")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt chiaweckerlHB =
                    Produkt.builder()
                            .id(2140)
                            .name("Chiaweckerl (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt dinkellaugenstangerl =
                    Produkt.builder()
                            .id(123)
                            .name("Dinkellaugenstangerl")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt dinkellaugenstangerlHB =
                    Produkt.builder()
                            .id(2123)
                            .name("Dinkellaugenstangerl (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt hirseweckerl =
                    Produkt.builder()
                            .id(152)
                            .name("Hirseweckerl")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt hirseweckerlHB =
                    Produkt.builder()
                            .id(2152)
                            .name("Hirseweckerl (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt salzstangerl =
                    Produkt.builder()
                            .id(118)
                            .name("Salzstangerl")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt salzbrezerlKlein =
                    Produkt.builder()
                            .id(156)
                            .name("Salzbrezerl Klein")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt pharaoamaranthweckerOhnHefe =
                    Produkt.builder()
                            .id(209)
                            .name("PharaoAmarantweckerl ohne Hefe")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt pharaoamaranthweckerOhnHefeHB =
                    Produkt.builder()
                            .id(2209)
                            .name("PharaoAmarantweckerl ohne Hefe (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt kornweckerlOhneHefe =
                    Produkt.builder()
                            .id(419)
                            .name("Kornweckerl ohne Hefe")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt kornweckerlOhneHefeHB =
                    Produkt.builder()
                            .id(2419)
                            .name("Kornweckerl ohne Hefe (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt dinkelweckerlOhneHefe =
                    Produkt.builder()
                            .id(418)
                            .name("Dinkelweckerl ohne Hefe")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt dinkelweckerlOhneHefeHB =
                    Produkt.builder()
                            .id(2418)
                            .name("Dinkelweckerl ohne Hefe (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt foccachia =
                    Produkt.builder()
                            .id(549)
                            .name("Foccachia")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt foccachiaHB =
                    Produkt.builder()
                            .id(2549)
                            .name("Foccachia (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt kuerbislaibchen =
                    Produkt.builder()
                            .id(154)
                            .name("Kürbislaibchen")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt kuerbislaibchenHB =
                    Produkt.builder()
                            .id(2154)
                            .name("Kürbislaibchen (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt basilikumweckerl =
                    Produkt.builder()
                            .id(750)
                            .name("Basilikumweckerl")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt basilikumweckerlHB =
                    Produkt.builder()
                            .id(2750)
                            .name("Basilikumweckerl (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt kuemmelweckerl =
                    Produkt.builder()
                            .id(750)
                            .name("Kümmelweckerl")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt kuemmelweckerlHB =
                    Produkt.builder()
                            .id(2750)
                            .name("Kümmelweckerl (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt baerlauchweckerl =
                    Produkt.builder()
                            .id(205)
                            .name("Bärlauchweckerl")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt baerlauchweckerlHB =
                    Produkt.builder()
                            .id(2205)
                            .name("Bärlauchweckerl (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt kurkumaweckerl =
                    Produkt.builder()
                            .id(743)
                            .name("Kurkumaweckerl")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt kurkumaweckerlHB =
                    Produkt.builder()
                            .id(2743)
                            .name("Kurkumaweckerl (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt rosinenweckerl =
                    Produkt.builder()
                            .id(453)
                            .name("Rosinenweckerl")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt rosinenweckerlHB =
                    Produkt.builder()
                            .id(2453)
                            .name("Rosinenweckerl (5)")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt nussMandelweckerl =
                    Produkt.builder()
                            .id(116)
                            .name("NussMandelweckerl")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt nussMandelweckerlHB =
                    Produkt.builder()
                            .id(2116)
                            .name("NussMandelweckerl (5)")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();


            Produkt studentenfutter =
                    Produkt.builder()
                            .id(368)
                            .name("Studentenfutter")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt studentenfutterHB =
                    Produkt.builder()
                            .id(2368)
                            .name("Studentenfutter (5)")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt dinkelSchnecke =
                    Produkt.builder()
                            .id(324)
                            .name("D-Schnecke")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt dinkelSchneckeHB =
                    Produkt.builder()
                            .id(2324)
                            .name("D-Schnecke (3)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt pharaobriochestriezerl =
                    Produkt.builder()
                            .id(110)
                            .name("Pharaobriochestriezerl")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt pharaobriochestriezerlHB =
                    Produkt.builder()
                            .id(2110)
                            .name("Pharaobriochestriezerl (3)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt topfengolatsche =
                    Produkt.builder()
                            .id(125)
                            .name("Topfengolatsche")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt topfengolatscheHB =
                    Produkt.builder()
                            .id(2125)
                            .name("Topfengolatsche teigig")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt powidlgolatsche =
                    Produkt.builder()
                            .id(126)
                            .name("Powidlgolatsche")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt powidlgolatscheHB =
                    Produkt.builder()
                            .id(2126)
                            .name("Powidlgolatsche teigig")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt nusskipferl =
                    Produkt.builder()
                            .id(132)
                            .name("Nusskipfer")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt nusskipferlHB =
                    Produkt.builder()
                            .id(2132)
                            .name("Nusskipfer teigig")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt mohnkipfer =
                    Produkt.builder()
                            .id(133)
                            .name("Mohnkipferl")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt mohnkipferHB =
                    Produkt.builder()
                            .id(2133)
                            .name("Mohnkipfer teigig")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt topfenmarille =
                    Produkt.builder()
                            .id(134)
                            .name("MohnkipfeTopfenmarille")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();


            Produkt topfenmarilleHB =
                    Produkt.builder()
                            .id(2134)
                            .name("MohnkipfeTopfenmarille teigig")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt dinkelcroissant =
                    Produkt.builder()
                            .id(241)
                            .name("Dinkelcroissant")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();


            Produkt dinkelcroissantHB =
                    Produkt.builder()
                            .id(2241)
                            .name("Dinkelcroissant teigig")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt Schokostanger =
                    Produkt.builder()
                            .id(415)
                            .name("Schokostangerl")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt schokostangerHB =
                    Produkt.builder()
                            .id(2415)
                            .name("Schokostangerl teigig")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt pharaocroissant =
                    Produkt.builder()
                            .id(363)
                            .name("Pharaocroissant ")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt pharaocroissantHB =
                    Produkt.builder()
                            .id(2363)
                            .name("Pharaocroissant teigig")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt ribiselstangerl =
                    Produkt.builder()
                            .id(406)
                            .name("Ribiselstangerl")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt ribiselstangerlHB =
                    Produkt.builder()
                            .id(2406)
                            .name("Ribiselstangerl teigig")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt nussino =
                    Produkt.builder()
                            .id(198)
                            .name("Nussino")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt nussinoHB =
                    Produkt.builder()
                            .id(2198)
                            .name("Nussino teigig")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt kastanienzipf =
                    Produkt.builder()
                            .id(308)
                            .name("Kastanienzipf")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt kastanienzipfHB =
                    Produkt.builder()
                            .id(2308)
                            .name("Kastanienzipf teigig")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt kirschpolster =
                    Produkt.builder()
                            .id(127)
                            .name("Kirschpolster")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt kirschpolsterHB =
                    Produkt.builder()
                            .id(2127)
                            .name("Kirschpolster teigig")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt apfeltascherl =
                    Produkt.builder()
                            .id(172)
                            .name("Apfeltascherl")
                            .bio(false)
                            .mehl(Mehl.Pharao)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt apfeltascherlHB =
                    Produkt.builder()
                            .id(2172)
                            .name("Apfeltascherl teigig")
                            .bio(false)
                            .mehl(Mehl.Pharao)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt birnentascherl =
                    Produkt.builder()
                            .id(210)
                            .name("Birnentascherl")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt birnentascherlHB =
                    Produkt.builder()
                            .id(2210)
                            .name("Birnentascherl teigig")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();


            Produkt kuerbiskipfer =
                    Produkt.builder()
                            .id(256)
                            .name("Kürbiskipferl")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt kuerbiskipferHB =
                    Produkt.builder()
                            .id(2256)
                            .name("Kürbiskipferl teigig")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt marzipankrone =
                    Produkt.builder()
                            .id(131)
                            .name("Marzipankrone")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt marzipankroneHB =
                    Produkt.builder()
                            .id(2131)
                            .name("Marzipankrone teigig")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt fruechteplunder =
                    Produkt.builder()
                            .id(177)
                            .name("Früchteplunder")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt marillenKrapfen =
                    Produkt.builder()
                            .id(191)
                            .name("Marillen Krapfen")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt vanillekKapfen =
                    Produkt.builder()
                            .id(173)
                            .name("Vanille Krapfen")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt powidlKrapfen =
                    Produkt.builder()
                            .id(151)
                            .name("Powidl Krapfen")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt marillenKrapfenVegan =
                    Produkt.builder()
                            .id(494)
                            .name("Marillen Krapfen vegan")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt marillenKrapfenYour =
                    Produkt.builder()
                            .id(139)
                            .name("Marillen Krapfen your")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt erdbeerkrapfen =
                    Produkt.builder()
                            .id(479)
                            .name("Erdbeerkrapfen")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt apfelsrtudel =
                    Produkt.builder()
                            .id(928)
                            .name("Apfelstrudel")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt topfensrtudel =
                    Produkt.builder()
                            .id(929)
                            .name("Topfenstrudel")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt marillensrtudel =
                    Produkt.builder()
                            .id(968)
                            .name("Marillenstrudel")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt birneMohnstrudel =
                    Produkt.builder()
                            .id(2002)
                            .name("Birne Mohnstrudel")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt obststrudel =
                    Produkt.builder()
                            .id(908)
                            .name("Obststrudel")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt egerlaender =
                    Produkt.builder()
                            .id(128)
                            .name("Egerländer")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(torten)
                            .build();

            Produkt pharaoLinzerschnitte =
                    Produkt.builder()
                            .id(269)
                            .name("Pharao Linzerschnitte")
                            .bio(true)
                            .mehl(Mehl.Pharao)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(torten)
                            .build();

            Produkt obstfleck =
                    Produkt.builder()
                            .id(135)
                            .name("Obstfleck")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(torten)
                            .build();

            Produkt birnenStreuselschnitte =
                    Produkt.builder()
                            .id(278)
                            .name("Birnen Streuselschnitte")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(torten)
                            .build();

            Produkt apfelschnitte =
                    Produkt.builder()
                            .id(511)
                            .name("Apfelschnitte")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(torten)
                            .build();

            Produkt mohnZitronenschnitte =
                    Produkt.builder()
                            .id(2001)
                            .name("Mohn Zitronenschnitte")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(torten)
                            .build();

            Produkt karottenroelchen =
                    Produkt.builder()
                            .id(2003)
                            .name("Karottenröllchen")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(torten)
                            .build();

            Produkt karottenZucchinischnitte =
                    Produkt.builder()
                            .id(323)
                            .name("Karotten Zucchinischnitte")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(torten)
                            .build();

            Produkt nussecke =
                    Produkt.builder()
                            .id(2004)
                            .name("Nussecke")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(torten)
                            .build();

            Produkt linzerstanger =
                    Produkt.builder()
                            .id(538)
                            .name("Linzerstanger")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(torten)
                            .build();

            Produkt linzerauge =
                    Produkt.builder()
                            .id(329)
                            .name("Linzerauge")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(torten)
                            .build();

            Produkt nougartherz =
                    Produkt.builder()
                            .id(425)
                            .name("Nougartherz")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(torten)
                            .build();

            Produkt sacherwuerfelHerzEi =
                    Produkt.builder()
                            .id(950)
                            .name("Sacherwürfel/Herz/Ei")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(torten)
                            .build();

            Produkt schokoberg =
                    Produkt.builder()
                            .id(691)
                            .name("Schockoberg")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(torten)
                            .build();

            Produkt striezelMitMandelspliter =
                    Produkt.builder()
                            .id(309)
                            .name("Striezel mit Mandelspliter")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt striezelOhneBestreuung =
                    Produkt.builder()
                            .id(381)
                            .name("Striezel ohne Betreuung")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();


            List<Produkt> produkte = Arrays.asList(
                    bauernbrot, roggenbrot, dinkelbrotOhneHefe, pharaobrotOhneHefe, dinkelbrot, dinkeltoast, 
                    leinsamenAmaranthbrotOhneHefe, backfermentbrotOhneHefe, karottenNussbrot, kraftbrotOhneHefe, 
                    chiabrotOhneHefe, dinkelbrotStange, bauernbrotStange, siebenkornStange, kuerbiskernbrot, 
                    suesskartoffelbrot, olivenbrot, roteRuebenbrot, zwiebelbrot, nussbrotstange, nussbrotleibRund,
                    baerlauchbrot, sandwich, baguette, kaesestangerl, vitalwecker, olinebweckerl, dinkelsemmel, 
                    dinkelkornsitz, dinkelkornspitzSalz, dinkelweckerl,  dinkelkornspitzOhneBestreu, pharaoweckerl, 
                    chiaweckerl, dinkellaugenstangerl, hirseweckerl, salzstangerl, salzbrezerlKlein, 
                    pharaoamaranthweckerOhnHefe, kornweckerlOhneHefe, dinkelweckerlOhneHefe, foccachia, 
                    kuerbislaibchen, basilikumweckerl, kuemmelweckerl, baerlauchweckerl, kurkumaweckerl,
                    rosinenweckerl, nussMandelweckerl, studentenfutter, dinkelSchnecke, pharaobriochestriezerl, 
                    topfengolatsche, powidlgolatsche, nusskipferl, mohnkipfer, topfenmarille, dinkelcroissant, 
                    Schokostanger, pharaocroissant, ribiselstangerl, nussino, kastanienzipf, kirschpolster, 
                    apfeltascherl, birnentascherl, marzipankrone, kuerbiskipfer, fruechteplunder, marillenKrapfen, 
                    vanillekKapfen, powidlKrapfen, marillenKrapfenVegan, marillenKrapfenYour, erdbeerkrapfen, 
                    apfelsrtudel, topfensrtudel, marillensrtudel, birneMohnstrudel, obststrudel, egerlaender, 
                    pharaoLinzerschnitte, obstfleck, birnenStreuselschnitte, apfelschnitte, mohnZitronenschnitte, 
                    karottenroelchen, karottenZucchinischnitte, nussecke, linzerstanger, linzerauge, nougartherz, 
                    sacherwuerfelHerzEi, schokoberg, striezelMitMandelspliter, striezelOhneBestreuung, bauernbrotHB,
                    dinkelbrotOhneHefeHB, dinkelbrotHB, leinsamenAmaranthbrotOhneHefeHB, karottenNussHB, kraftbrotOhneHefeHB,
                    chiabrotOhneHefeHB, kuerbislaibchenHB, kuerbisbrotHB, suesskartoffelbrotHB, olivenbrotHB, roteRuebenbrotHB,
                    zwiebelbrotHB, nussbrotlaibRundHB, baerlauchbrotHB, baguetteHB, kaesestangerlHB, vitalweckerHB,
                    olivenweckerlHB, dinkelsemmelHB, dinkelkornsitzHB, dinkelweckerlHB, dinkelkornspitzOhneBestreuHB, pharaoweckerlHB,
                    chiaweckerlHB, dinkellaugenstangerlHB, hirseweckerlHB, pharaoamaranthweckerOhnHefeHB, kornweckerlOhneHefeHB,
                    foccachiaHB, basilikumweckerlHB, kuemmelweckerlHB, rosinenweckerlHB, nussMandelweckerlHB, baerlauchweckerlHB,
                    dinkelweckerlOhneHefeHB, kurkumaweckerlHB, studentenfutterHB, dinkelSchneckeHB, pharaobriochestriezerlHB,
                    topfengolatscheHB, powidlgolatscheHB, nusskipferlHB, mohnkipferHB, topfenmarilleHB, dinkelcroissantHB,
                    schokostangerHB, pharaocroissantHB, ribiselstangerlHB, nussinoHB, kastanienzipfHB, kirschpolsterHB,
                    apfeltascherlHB, birnentascherlHB, kuerbiskipferHB, marzipankroneHB
            );


            

            produktRepository.saveAll(produkte);
        };
    }
}
