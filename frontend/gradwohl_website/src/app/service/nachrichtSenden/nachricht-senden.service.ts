import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Globals } from 'src/global/global';
import { nachrichtSenden } from 'src/model/nachrichtSenden/nachrichtSenden';

@Injectable({
  providedIn: 'root'
})
export class NachrichtSendenService {

  private baseUri: string = this.global.backendUri;

  constructor(private http: HttpClient,
              private global: Globals) { }

  
  getNachrichtSendenByFiliale(filialeId: number){
    return this.http.get(this.baseUri + '/nachrichtSenden/senden/'+filialeId)
  }

  getNachrichtSendenByFilialeAndNachrichtId(filialeId: number, nachrichtId: number){
    return this.http.get(this.baseUri + '/nachrichtSenden/senden/'+filialeId+'/'+nachrichtId)
  }

  getNachrichtSendenByNachrichtId(nachrichtId: number){
    return this.http.get(this.baseUri + '/nachrichtSenden/senden/nachrichtId/'+nachrichtId)
  }

  insertNachrichtSenden(nachrichtSendenToInsert: nachrichtSenden[]){
    return this.http.post(this.baseUri+'/nachrichtSenden/senden', nachrichtSendenToInsert)
  }

  deleteNachrichtSenden(nachrichtId: number, filialeId: number){
    return this.http.delete(this.baseUri+'/nachrichtSenden/senden/'+nachrichtId+'/'+filialeId)
  }

  updateNachrichtSenden(nachrichtId: number, filialeId: number, nachrichtSendenToInsert: nachrichtSenden){
    return this.http.put(this.baseUri+'/nachrichtSenden/senden/'+filialeId+'/'+nachrichtId, nachrichtSendenToInsert)
  }
}
