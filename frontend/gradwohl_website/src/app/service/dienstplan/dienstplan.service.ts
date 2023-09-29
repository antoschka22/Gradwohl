import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Globals } from 'src/global/global';
import { dienstplan } from 'src/model/dienstplan/dienstplan';

@Injectable({
  providedIn: 'root'
})
export class DienstplanService {

  private baseUri: string = this.global.backendUri;

  constructor(private http: HttpClient,
              private global: Globals) { }

  
  getDienstplanByFiliale(filialeId: number){
    return this.http.get(this.baseUri + '/dienstplan/'+filialeId)
  }

  insertDienstplan(dienstplanToInsert: dienstplan){
    return this.http.post(this.baseUri+'/dienstplan', dienstplanToInsert)
  }

  updateDienstplan(datum: Date, filialeId: number, von: Date, mitarbeiterId: number, dienstplanToInsert: dienstplan){
    return this.http.put(this.baseUri+'/dienstplan/'+datum+'/'+filialeId+'/'+von+'/'+mitarbeiterId, dienstplanToInsert)
  }

  deleteDienstplan(datum: Date, filialeId: number, von: Date, mitarbeiterId: number){
    return this.http.delete(this.baseUri+'/dienstplan/'+datum+'/'+filialeId+'/'+von+'/'+mitarbeiterId)
  }
}
