import { Role } from './Role';
export interface Utilisateur{
  id?: number;
  userName: string;

  password: string;

  roles: Role[];

}