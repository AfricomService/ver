import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IIncident, Incident } from 'app/shared/model/incident.model';
import { IncidentService } from './incident.service';

@Component({
  selector: 'jhi-incident-update',
  templateUrl: './incident-update.component.html',
})
export class IncidentUpdateComponent implements OnInit {
  isSaving = false;
  discoveryDateDp: any;
  mitigationDateDp: any;

  editForm = this.fb.group({
    id: [],
    incidentId: [null, [Validators.required]],
    description: [null, [Validators.required]],
    discoveryDate: [null, [Validators.required]],
    severity: [],
    status: [null, [Validators.required]],
    responsible: [],
    mitigationDate: [],
  });

  constructor(protected incidentService: IncidentService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ incident }) => {
      this.updateForm(incident);
    });
  }

  updateForm(incident: IIncident): void {
    this.editForm.patchValue({
      id: incident.id,
      incidentId: incident.incidentId,
      description: incident.description,
      discoveryDate: incident.discoveryDate,
      severity: incident.severity,
      status: incident.status,
      responsible: incident.responsible,
      mitigationDate: incident.mitigationDate,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const incident = this.createFromForm();
    if (incident.id !== undefined) {
      this.subscribeToSaveResponse(this.incidentService.update(incident));
    } else {
      this.subscribeToSaveResponse(this.incidentService.create(incident));
    }
  }

  private createFromForm(): IIncident {
    return {
      ...new Incident(),
      id: this.editForm.get(['id'])!.value,
      incidentId: this.editForm.get(['incidentId'])!.value,
      description: this.editForm.get(['description'])!.value,
      discoveryDate: this.editForm.get(['discoveryDate'])!.value,
      severity: this.editForm.get(['severity'])!.value,
      status: this.editForm.get(['status'])!.value,
      responsible: this.editForm.get(['responsible'])!.value,
      mitigationDate: this.editForm.get(['mitigationDate'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IIncident>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
