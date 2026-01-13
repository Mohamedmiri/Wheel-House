import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ClientComponent } from './client/client.component';
import {UtilisateurComponent} from './utilisateur/utilisateur.component'

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [ClientComponent, UtilisateurComponent],
  templateUrl: './app.html'
})
export class App {}
