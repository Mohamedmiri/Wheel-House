import { Genre } from './Genre';

export interface Client {
  id?: number;

  nomClient: string;
  prenomClient: string;
  telephone: string;
  cin: string;


  dateNaissance: string;

  ville: string;
  paye: string;

  genre: Genre;
}
