import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Utilisateur } from '../model/Utilisateur';

@Injectable({
  providedIn: 'root',
})
export class UtilisateurService {
  private baseUrl = 'http://localhost:8083/api/user';

  constructor(private http: HttpClient) {}
  getAllUsers():Observable<Utilisateur[]>{
    return this.http.get<Utilisateur[]>(this.baseUrl);
  }
  getUserById(id: number): Observable<Utilisateur> {
    return this.http.get<Utilisateur>(`${this.baseUrl}/${id}`);
  }


  saveUser(dto: Utilisateur): Observable<string> {
    return this.http.post(`${this.baseUrl}`, dto, { responseType: 'text' });
  }


  updateUserByUsername(username: string, dto: Utilisateur): Observable<Utilisateur> {
    return this.http.put<Utilisateur>(`${this.baseUrl}/username/${username}`, dto);
  }


  deleteUser(id: number): Observable<string> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

}
