import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { ClientService } from '../services/client.service';
import { Client } from '../model/Client';
import { Genre } from '../model/Genre';

@Component({
  selector: 'app-client',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css'] 
})
export class ClientComponent implements OnInit {
  clients: Client[] = [];
  message = '';

  genres = Object.values(Genre);

  selectedId?: number;

  // MODALS
  isFormModalOpen = false;
  isDeleteModalOpen = false;
  clientToDeleteId?: number;

  form: Client = {
    nomClient: '',
    prenomClient: '',
    telephone: '',
    cin: '',
    dateNaissance: '',
    ville: '',
    paye: '',
    genre: Genre.HOMME
  };

  constructor(private clientService: ClientService) {}

  ngOnInit(): void {
    this.loadClients();
  }

  loadClients(): void {
    this.clientService.getAllClients().subscribe({
      next: (data) => (this.clients = data),
      error: (err) => (this.message = 'Erreur chargement clients: ' + (err?.message ?? err))
    });
  }

  selectClient(c: Client): void {
    this.selectedId = c.id;

    const date = (c.dateNaissance ?? '').toString().slice(0, 10);
    this.form = { ...c, dateNaissance: date };

    this.message = '';
  }

  resetForm(): void {
    this.selectedId = undefined;
    this.form = {
      nomClient: '',
      prenomClient: '',
      telephone: '',
      cin: '',
      dateNaissance: '',
      ville: '',
      paye: '',
      genre: Genre.HOMME
    };
    this.message = '';
  }

  // CRUD SAVE
  save(): void {
    if (!this.form.nomClient?.trim() || !this.form.prenomClient?.trim()) {
      this.message = 'Nom et prénom sont obligatoires.';
      return;
    }

    if (this.selectedId) {
      this.clientService.updateClient(this.selectedId, this.form).subscribe({
        next: () => {
          this.message = 'Client modifié ✅';
          this.loadClients();
          this.resetForm();
          this.closeFormModal();
        },
        error: (err) => (this.message = 'Erreur update: ' + (err?.message ?? err))
      });
    } else {
      this.clientService.createClient(this.form).subscribe({
        next: () => {
          this.message = 'Client créé ✅';
          this.loadClients();
          this.resetForm();
          this.closeFormModal();
        },
        error: (err) => (this.message = 'Erreur create: ' + (err?.message ?? err))
      });
    }
  }

  // CRUD DELETE (sans confirm ici)
  delete(id?: number): void {
    if (!id) return;

    this.clientService.deleteClient(id).subscribe({
      next: (res) => {
        this.message = res || 'Client supprimé ✅';
        this.loadClients();
        if (this.selectedId === id) this.resetForm();
      },
      error: (err) => (this.message = 'Erreur delete: ' + (err?.message ?? err))
    });
  }

  // ===== MODAL HANDLERS =====
  openCreateModal(): void {
    this.resetForm();
    this.isFormModalOpen = true;
  }

  openEditModal(c: Client): void {
    this.selectClient(c);
    this.isFormModalOpen = true;
  }

  closeFormModal(): void {
    this.isFormModalOpen = false;
  }

  openDeleteModal(id?: number): void {
    if (!id) return;
    this.clientToDeleteId = id;
    this.isDeleteModalOpen = true;
  }

  closeDeleteModal(): void {
    this.isDeleteModalOpen = false;
    this.clientToDeleteId = undefined;
  }

  confirmDelete(): void {
    if (!this.clientToDeleteId) return;
    this.delete(this.clientToDeleteId);
    this.closeDeleteModal();
  }
}
