import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { IncidentService } from 'app/entities/incident/incident.service';
import { IIncident, Incident } from 'app/shared/model/incident.model';
import { IncidentSeverity } from 'app/shared/model/enumerations/incident-severity.model';

describe('Service Tests', () => {
  describe('Incident Service', () => {
    let injector: TestBed;
    let service: IncidentService;
    let httpMock: HttpTestingController;
    let elemDefault: IIncident;
    let expectedResult: IIncident | IIncident[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(IncidentService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Incident(0, 0, 'AAAAAAA', currentDate, IncidentSeverity.LOW, 'AAAAAAA', 'AAAAAAA', currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            discoveryDate: currentDate.format(DATE_FORMAT),
            mitigationDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Incident', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            discoveryDate: currentDate.format(DATE_FORMAT),
            mitigationDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            discoveryDate: currentDate,
            mitigationDate: currentDate,
          },
          returnedFromService
        );

        service.create(new Incident()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Incident', () => {
        const returnedFromService = Object.assign(
          {
            incidentId: 1,
            description: 'BBBBBB',
            discoveryDate: currentDate.format(DATE_FORMAT),
            severity: 'BBBBBB',
            status: 'BBBBBB',
            responsible: 'BBBBBB',
            mitigationDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            discoveryDate: currentDate,
            mitigationDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Incident', () => {
        const returnedFromService = Object.assign(
          {
            incidentId: 1,
            description: 'BBBBBB',
            discoveryDate: currentDate.format(DATE_FORMAT),
            severity: 'BBBBBB',
            status: 'BBBBBB',
            responsible: 'BBBBBB',
            mitigationDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            discoveryDate: currentDate,
            mitigationDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Incident', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
