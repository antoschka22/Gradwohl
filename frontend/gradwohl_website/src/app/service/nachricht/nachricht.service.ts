import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Globals } from 'src/global/global';
import { nachricht } from 'src/model/nachricht/nachricht';

@Injectable({
  providedIn: 'root'
})
export class NachrichtService {

  private baseUri: string = this.global.backendUri;

  constructor(private http: HttpClient,
              private global: Globals) { }

  getAllNachrichten(){
    return this.http.get(this.baseUri + '/nachricht')
  }

  insertNachricht(nachrichtToInsert: nachricht){
    return this.http.post(this.baseUri+'/nachricht', nachrichtToInsert)
  }

  deleteNachricht(id: number){
    return this.http.delete(this.baseUri+'/nachricht/'+id)
  }

  updateNachricht(id: number, nachrichtToInsert: nachricht){
    return this.http.put(this.baseUri+'/nachricht/'+id, nachrichtToInsert)
  }

}
