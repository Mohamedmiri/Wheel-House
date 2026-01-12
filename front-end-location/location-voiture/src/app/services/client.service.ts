import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Client } from '../model/Client';

@Injectable({ providedIn: 'root' })
export class ClientService {
  private baseUrl = 'http://localhost:8083/api/clients';

  constructor(private http: HttpClient) {}

  getAllClients(): Observable<Client[]> {
    return this.http.get<Client[]>(this.baseUrl);
  }

  getClientById(id: number): Observable<Client> {
    return this.http.get<Client>(`${this.baseUrl}/${id}`);
  }

  createClient(dto: Client): Observable<Client> {
    return this.http.post<Client>(this.baseUrl, dto);
  }

  updateClient(id: number, dto: Client): Observable<Client> {
    return this.http.put<Client>(`${this.baseUrl}/${id}`, dto);
  }

  deleteClient(id: number): Observable<string> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }
}
