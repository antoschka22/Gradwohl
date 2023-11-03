import { filiale } from "../filiale/filiale";
import { mitabeiter } from "../mitarbeiter/mitarbeiter";

export interface dienstplanId{
    datum: string,
    filiale: filiale,
    von: string,
    mitarbeiter: mitabeiter
}