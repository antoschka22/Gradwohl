import { filiale } from "../filiale/filiale";
import { nachricht } from "../nachricht/nachricht";

export interface nachrichtSendenId{
    filiale: filiale,
    nachricht: nachricht
}