import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'project',
        loadChildren: () => import('./project/project.module').then(m => m.VerprojectsProjectModule),
      },
      {
        path: 'task',
        loadChildren: () => import('./task/task.module').then(m => m.VerprojectsTaskModule),
      },
      {
        path: 'stakeholder',
        loadChildren: () => import('./stakeholder/stakeholder.module').then(m => m.VerprojectsStakeholderModule),
      },
      {
        path: 'resource',
        loadChildren: () => import('./resource/resource.module').then(m => m.VerprojectsResourceModule),
      },
      {
        path: 'comment',
        loadChildren: () => import('./comment/comment.module').then(m => m.VerprojectsCommentModule),
      },
      {
        path: 'incident',
        loadChildren: () => import('./incident/incident.module').then(m => m.VerprojectsIncidentModule),
      },
      {
        path: 'risk',
        loadChildren: () => import('./risk/risk.module').then(m => m.VerprojectsRiskModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class VerprojectsEntityModule {}
