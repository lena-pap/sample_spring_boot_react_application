import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';

class WalletEdit extends Component {

    emptyItem = {
        name: '',
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        const wallet = await (await fetch(`/wallets/${this.props.match.params.id}`)).json();
        this.setState({item: wallet});
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {item} = this.state;

        const action = document.activeElement.id;

        await fetch('/wallets/' + item.id + '/' + action, {
            method: 'PATCH',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: item.amountInCents,
        });
        this.props.history.push('/wallets');
    }

    render() {
        const {item} = this.state;
        const title = <h2>Edit Wallet</h2>;
    
        return <div>
            <AppNavbar/>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="amountInCents">Amount</Label>
                        <Input type="text" name="amountInCents" id="amountInCents" value={item.amountInCents || ''}
                               onChange={this.handleChange} autoComplete="Amount in cents"/>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit" id="withdraw">Withdraw</Button>{' '}
                        <Button color="primary" type="submit" id="add">Add</Button>{' '}
                        <Button color="secondary" tag={Link} to="/wallets">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}
export default withRouter(WalletEdit);