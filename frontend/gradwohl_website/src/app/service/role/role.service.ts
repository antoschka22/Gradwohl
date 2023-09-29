import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Globals } from 'src/global/global';
import { role } from 'src/model/role/role';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  private baseUri: string = this.global.backendUri;

  constructor(private http: HttpClient,
              private global: Globals) { }

  getRolen(){
    return this.http.get(this.baseUri + '/role')
  }

  insertRole(roleToInsert: role){
    return this.http.post(this.baseUri+'/role', roleToInsert)
  }

  deleteRole(id: string){
    return this.http.delete(this.baseUri+'/role/'+id)
  }

  updateRole(id: string, roleToInsert: role){
    return this.http.put(this.baseUri+'/role', roleToInsert)
  }
}
