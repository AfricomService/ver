import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IStakeholder, Stakeholder } from 'app/shared/model/stakeholder.model';
import { StakeholderService } from './stakeholder.service';
import { StakeholderComponent } from './stakeholder.component';
import { StakeholderDetailComponent } from './stakeholder-detail.component';
import { StakeholderUpdateComponent } from './stakeholder-update.component';

@Injectable({ providedIn: 'root' })
export class StakeholderResolve implements Resolve<IStakeholder> {
  constructor(private service: StakeholderService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IStakeholder> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((stakeholder: HttpResponse<Stakeholder>) => {
          if (stakeholder.body) {
            return of(stakeholder.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Stakeholder());
  }
}

export const stakeholderRoute: Routes = [
  {
    path: '',
    component: StakeholderComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'verprojectsApp.stakeholder.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: StakeholderDetailComponent,
    resolve: {
      stakeholder: StakeholderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'verprojectsApp.stakeholder.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: StakeholderUpdateComponent,
    resolve: {
      stakeholder: StakeholderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'verprojectsApp.stakeholder.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: StakeholderUpdateComponent,
    resolve: {
      stakeholder: StakeholderResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'verprojectsApp.stakeholder.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
