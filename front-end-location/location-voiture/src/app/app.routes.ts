import { Routes } from '@angular/router';
import { ClientComponent } from './client/client.component';
import { UtilisateurComponent } from './utilisateur/utilisateur.component';

export const routes: Routes = [
  { path: '', component: ClientComponent},
  { path: '', component: UtilisateurComponent }
];
