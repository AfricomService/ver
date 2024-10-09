import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRisk, Risk } from 'app/shared/model/risk.model';
import { RiskService } from './risk.service';

@Component({
  selector: 'jhi-risk-update',
  templateUrl: './risk-update.component.html',
})
export class RiskUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    riskId: [null, [Validators.required]],
    description: [null, [Validators.required]],
    probability: [],
    impact: [],
    status: [],
  });

  constructor(protected riskService: RiskService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ risk }) => {
      this.updateForm(risk);
    });
  }

  updateForm(risk: IRisk): void {
    this.editForm.patchValue({
      id: risk.id,
      riskId: risk.riskId,
      description: risk.description,
      probability: risk.probability,
      impact: risk.impact,
      status: risk.status,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const risk = this.createFromForm();
    if (risk.id !== undefined) {
      this.subscribeToSaveResponse(this.riskService.update(risk));
    } else {
      this.subscribeToSaveResponse(this.riskService.create(risk));
    }
  }

  private createFromForm(): IRisk {
    return {
      ...new Risk(),
      id: this.editForm.get(['id'])!.value,
      riskId: this.editForm.get(['riskId'])!.value,
      description: this.editForm.get(['description'])!.value,
      probability: this.editForm.get(['probability'])!.value,
      impact: this.editForm.get(['impact'])!.value,
      status: this.editForm.get(['status'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRisk>>): void {
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
