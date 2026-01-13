import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { UtilisateurService } from '../services/utilisateur.service';
import { Utilisateur } from '../model/Utilisateur';

@Component({
  selector: 'app-utilisateur',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './utilisateur.component.html',
  styleUrls: ['./utilisateur.component.css'],
})
export class UtilisateurComponent implements OnInit {
  users: Utilisateur[] = [];
  message = '';

  selectedId?: number;
  selectedUsername?: string;

  // Modals
  isDeleteModalOpen = false;
  userToDeleteId?: number;

  form: Utilisateur = {
    userName: '',
    password: ''
    // roles: [] // décommente si ton modèle l'a
  } as Utilisateur;

  constructor(private userService: UtilisateurService) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.userService.getAllUsers().subscribe({
      next: (data) => (this.users = data),
      error: (err) => (this.message = 'Erreur chargement: ' + (err?.message ?? err)),
    });
  }

  selectUser(u: Utilisateur): void {
    this.selectedId = u.id;
    this.selectedUsername = u.userName;
    // sécurité: on vide le password pour ne pas l’afficher
    this.form = { ...u, password: '' } as Utilisateur;
    this.message = '';
  }

  resetForm(): void {
    this.selectedId = undefined;
    this.selectedUsername = undefined;
    this.form = { userName: '', password: '' } as Utilisateur;
    this.message = '';
  }

  createUser(): void {
    if (!this.form.userName?.trim() || !this.form.password?.trim()) {
      this.message = 'Username et password sont obligatoires.';
      return;
    }

    this.userService.saveUser(this.form).subscribe({
      next: (res) => {
        this.message = res || 'Utilisateur ajouté ✅';
        this.loadUsers();
        this.resetForm();
      },
      error: (err) => (this.message = 'Erreur ajout: ' + (err?.message ?? err)),
    });
  }

  updateUser(): void {
    if (!this.selectedUsername) {
      this.message = 'Sélectionne un utilisateur à modifier.';
      return;
    }

    this.userService.updateUserByUsername(this.selectedUsername, this.form).subscribe({
      next: () => {
        this.message = 'Utilisateur modifié ✅';
        this.loadUsers();
        this.resetForm();
      },
      error: (err) => (this.message = 'Erreur update: ' + (err?.message ?? err)),
    });
  }

  // ===== Delete popup =====
  openDeleteModal(id?: number): void {
    if (!id) return;
    this.userToDeleteId = id;
    this.isDeleteModalOpen = true;
  }

  closeDeleteModal(): void {
    this.isDeleteModalOpen = false;
    this.userToDeleteId = undefined;
  }

  confirmDelete(): void {
    if (!this.userToDeleteId) return;

    this.userService.deleteUser(this.userToDeleteId).subscribe({
      next: (res) => {
        this.message = res || 'Utilisateur supprimé ✅';
        this.loadUsers();
        if (this.selectedId === this.userToDeleteId) this.resetForm();
      },
      error: (err) => (this.message = 'Erreur delete: ' + (err?.message ?? err)),
    });

    this.closeDeleteModal();
  }
  getRolesText(u: Utilisateur): string {
  const roles = u.roles ?? [];
  return roles.map(r => r.name).join(', ');
}

}
