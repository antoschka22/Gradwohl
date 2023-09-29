import { filiale } from "../filiale/filiale"
import { produkt } from "../produkt/produkt"

export interface kundenbestellungId{
    datum: Date,
    produkt: produkt,
    kunde: string,
    filiale: filiale
}