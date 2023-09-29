import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Globals } from 'src/global/global';
import { vorlage } from 'src/model/vorlage/vorlage';
import { vorlageId } from 'src/model/vorlage/vorlageId';

@Injectable({
  providedIn: 'root'
})
export class VorlageService {

  private baseUri: string = this.global.backendUri;

  constructor(private http: HttpClient,
              private global: Globals) { }

  getAllVorlage(){
    return this.http.get(this.baseUri + '/vorlage')
  }

  getVorlageById(id: number){
    return this.http.get(this.baseUri + '/vorlage/'+id)
  }

  getVorlageByFiliale(filialeId: number){
    return this.http.get(this.baseUri+'/vorlage/filiale/'+filialeId)
  }

  insertVorlage(vorlageToInsert: vorlage){
    return this.http.post(this.baseUri+'/vorlage', vorlageToInsert)
  }

  updateVorlage(id: number, filialeId: number, produktId: number, vorlageToUpdate: vorlage){
    return this.http.put(this.baseUri+'/vorlage/'+id+'/'+produktId+'/'+filialeId, vorlageToUpdate)
  }

  deleteVorlage(id: number, filialeId: number, produktId: number){
    return this.http.delete(this.baseUri+'/vorlage/'+id+'/'+produktId+'/'+filialeId)
  }
}
