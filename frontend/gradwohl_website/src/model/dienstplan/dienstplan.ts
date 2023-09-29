import { dienstplanId } from "./dienstplanId"

export interface dienstplan{
    id: dienstplanId,
    bis: Date,
    anfrage: boolean
}