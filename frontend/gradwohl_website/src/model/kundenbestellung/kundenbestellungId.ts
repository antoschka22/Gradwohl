import { filiale } from "../filiale/filiale"
import { produkt } from "../produkt/produkt"

export interface kundenbestellungId{
    datum: string,
    produkt: produkt,
    kunde: string,
    filiale: filiale
}