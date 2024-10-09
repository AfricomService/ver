import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { VerprojectsSharedModule } from 'app/shared/shared.module';
import { IncidentComponent } from './incident.component';
import { IncidentDetailComponent } from './incident-detail.component';
import { IncidentUpdateComponent } from './incident-update.component';
import { IncidentDeleteDialogComponent } from './incident-delete-dialog.component';
import { incidentRoute } from './incident.route';

@NgModule({
  imports: [VerprojectsSharedModule, RouterModule.forChild(incidentRoute)],
  declarations: [IncidentComponent, IncidentDetailComponent, IncidentUpdateComponent, IncidentDeleteDialogComponent],
  entryComponents: [IncidentDeleteDialogComponent],
})
export class VerprojectsIncidentModule {}
