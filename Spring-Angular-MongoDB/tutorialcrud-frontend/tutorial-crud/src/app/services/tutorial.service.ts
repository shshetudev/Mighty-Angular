import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Tutorial} from '../model/tutorial';

const baseUrl = 'http://127.0.0.1:8080/api/tutorials';

@Injectable({
  providedIn: 'root'
})
export class TutorialService {
  constructor(private http: HttpClient) {
  }

  // Fetch Operation
  getAll(): Observable<Tutorial[]> {
    return this.http.get<Tutorial[]>(baseUrl);
  }

  getById(id): Observable<Tutorial> {
    return this.http.get<Tutorial>(`${baseUrl}/${id}`);
  }

 findByTitle(title): Observable<Tutorial[]> {
    return this.http.get<Tutorial[]>(`${baseUrl}?title=${title}`);
 }

  // Add Operation
  create(data): Observable<Tutorial> {
    return this.http.post<Tutorial>(baseUrl, data);
  }

  // Update Operation
  update(id, data): Observable<Tutorial> {
    return this.http.put<Tutorial>(`${baseUrl}/${id}`, data);
  }

  // todo: Use specified Object based response
  // Delete Operation
  delete(id): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(baseUrl);
  }

}
