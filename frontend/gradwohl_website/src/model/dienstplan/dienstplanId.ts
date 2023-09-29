import { Timestamp } from "rxjs";
import { filiale } from "../filiale/filiale";
import { mitabeiter } from "../mitarbeiter/mitarbeiter";

export interface dienstplanId{
    datum: Date,
    filiale: filiale,
    von: Date,
    mitarbeiter: mitabeiter
}