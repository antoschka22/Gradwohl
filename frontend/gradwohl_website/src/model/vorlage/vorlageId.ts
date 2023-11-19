import { filiale } from "../filiale/filiale";
import { produkt } from "../produkt/produkt";

export interface vorlageId{
    name: string,
    produkt: produkt,
    filiale: filiale
}